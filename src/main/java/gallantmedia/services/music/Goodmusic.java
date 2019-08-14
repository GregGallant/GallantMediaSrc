package gallantmedia.services.music;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;

import com.google.gson.JsonElement;
import gallantmedia.services.news.Goodnews;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public class Goodmusic {

    private String mNext;

    protected Logger logger = LoggerFactory.getLogger(Goodmusic.class);

    /**
     * @param artist_name
     * @return
     */
    public String renderArtist(String artist_name)
    {
          String artistUrl = "https://api.deezer.com/search/artist/?q="+artist_name+"&index=0&limit=1";
          JsonElement musicJson  = this.getApiResponse(artistUrl, false);
          JsonElement musicDataElement = musicJson.getAsJsonObject().get("data");
          //JsonElement artist_id_element = musicDataElement.getAsJsonObject().get("id");
          JsonArray musicAsJEArr = musicDataElement.getAsJsonArray();
          //JsonElement tracklist_element = musicDataElement.getAsJsonObject().get("tracklist");

          //HashMap<String,HashMap<HashMap<String, List<String>>, HashMap<String, List<String>>>> musicSheet = new HashMap<>();
          HashMap<String, HashMap<String, List<String>>> musicSheet = new HashMap<>();
          HashMap<String, List<String>> artistSheet = new HashMap<>();
          HashMap<HashMap<String, List<String>>, HashMap<String, List<String>>> albumsAndTracks = new HashMap<>();
          HashMap<Integer, List<JsonElement>> eachAlbum = new HashMap<>();
          HashMap<String, List<String>> eachTrack = new HashMap<>();

          GsonBuilder builder = new GsonBuilder();

          List<String> musicArr = new ArrayList<>();
          for (JsonElement e : musicAsJEArr) {
              musicArr.add(e.getAsJsonObject().get("id").toString());
              musicArr.add(e.getAsJsonObject().get("name").toString());
              musicArr.add(e.getAsJsonObject().get("picture_medium").toString());
              musicArr.add(e.getAsJsonObject().get("picture_big").toString());
          }

          artistSheet.put("artist", musicArr);

          String artistName = musicArr.get(1).replaceAll(" ", "%20");
          String artistId = musicArr.get(0);

          if (artistName != null && !artistName.equals("")) {
              artistName = cleanString(artistName);
              JsonElement albumList = renderAlbumsByArtistName(artistName);

              JsonElement albumListData = albumList.getAsJsonObject().get("data");
              JsonArray albumAsJEArr = albumListData.getAsJsonArray();

              int gindex = 0;
              //HashMap<List<String>, List<String>> allTracks = new HashMap<>();

              for (JsonElement e : albumAsJEArr) {

                  JsonElement artistToMatch = e.getAsJsonObject().get("artist");
                  String artistIdToMatch = artistToMatch.getAsJsonObject().get("id").toString();
                   if (!artistId.equals(artistIdToMatch)) {
                        continue;
                   }

                   List<JsonElement> albumArr = new ArrayList<>();
                   albumArr.add(e.getAsJsonObject().get("id"));
                   albumArr.add(e.getAsJsonObject().get("title"));
                   albumArr.add(e.getAsJsonObject().get("cover_medium"));
                   albumArr.add(e.getAsJsonObject().get("cover_big"));
                   albumArr.add(e.getAsJsonObject().get("nb_tracks"));
                   albumArr.add(e.getAsJsonObject().get("tracklist"));

                   logger.info("artistID To Match: " + artistIdToMatch);


                  String tracklistUrl = e.getAsJsonObject().get("tracklist").toString();
                  if (tracklistUrl != null && tracklistUrl.contains("http")) {
                      JsonElement tracklist = renderTracksByAlbum(tracklistUrl);
                      JsonElement trackListData = tracklist.getAsJsonObject().get("data");

                      Gson gson = builder.create();
                      String tracklistJSON = gson.toJson(trackListData);
                      albumArr.add(tracklist);


                      /*
                      JsonArray tracksAsJEArr = trackListData.getAsJsonArray();
                      for (JsonElement t : tracksAsJEArr) {
                          List<String> tracksArr = new ArrayList<>();
                          tracksArr.add(t.getAsJsonObject().get("id").toString());
                          tracksArr.add(t.getAsJsonObject().get("title").toString());
                          tracksArr.add(t.getAsJsonObject().get("title_short").toString());
                          tracksArr.add(t.getAsJsonObject().get("link").toString());
                          tracksArr.add(t.getAsJsonObject().get("preview").toString());
                          tracksArr.add(t.getAsJsonObject().get("duration").toString());

                          eachTrack.put("track_"+albumArr.get(0), tracksArr);
                      }
                      */
                      //albumsAndTracks.put(eachAlbum, eachTrack);
                  }
                  eachAlbum.put(gindex, albumArr);
                  gindex++;
              }

              //albumsAndTracks.put(albumArr, tracksArr);

          }

          //musicSheet.put("albums", eachAlbum);

          Gson gson = builder.create();
          //logger.info("MusicSheet Artist: ", musicSheet.get("artist"));
          //logger.info("MusicSheet Album: ", musicSheet.get("albums"));

          String musicSheetJson = gson.toJson(eachAlbum);
          //musicSheetJson = musicSheetJson.replace("\\\"", "\"");
          //musicSheetJson = musicSheetJson.replace("\"\"", "\"");
          //logger.info("MusicSheetJSON: " + musicSheetJson);

          //String musicString = gson.toJson(musicJson);
          //logger.info("Final String thusfar: " + musicString);
          return musicSheetJson;
    }


    /**
     *
     * @param artistName
     * @return
     */
    protected JsonElement renderAlbumsByArtistName(String artistName)
    {
        String getAlbumUrl = "https://api.deezer.com/search/album/?q="+artistName+"&index=0&limit=95";
        JsonElement albumData = this.getApiResponse(getAlbumUrl, false);
        // TODO: Match the artist id

        return albumData;
    }

    /**
     *
     * @param trackUrl
     * @return
     */
    protected JsonElement renderTracksByAlbum(String trackUrl)
    {
        trackUrl = trackUrl.replaceAll("\"", "");
        //logger.info("TRACK URL: " + trackUrl);
        JsonElement trackData = this.getApiResponse(trackUrl, false);
        return trackData;
    }

    /**
     *
     * @param mUrl Server URL
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    protected JsonElement getApiResponse(String mUrl, boolean needsProtocol)
    {

        HttpsURLConnection connection = null;
        URL url;
        String protocol = "https";
        String file = "/?1";
        StringBuilder response = new StringBuilder();
        InputStream is = null;
        BufferedReader rd = null;

        try {
            if (needsProtocol) {
                mUrl = mUrl.replaceAll("https://", "");
                url = new URL(protocol, mUrl, file);
            } else {
               // logger.info("URL: " + mUrl);
                url = new URL(mUrl);
            }
            //logger.info("URL object: " + url);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
        } catch(ProtocolException pe) {
            logger.error("PE Exception: " + pe.getMessage());
        } catch(IOException ie) {
            logger.error("Connection IO Exception: " + ie.getMessage());
        }


        try {
            if (connection != null) {
                is = connection.getInputStream();
            }

            if (is != null) {
                rd = new BufferedReader(new InputStreamReader(is));
            }

            if (rd != null) {
                String line;
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                }

                rd.close();
            }
        } catch(IOException ie) {
            logger.error("InputStream IO Exception: " + ie.getMessage());
        } catch(NullPointerException ne) {
            logger.error("NPE Exception: " + ne.getMessage());
        }


        JsonParser parser = new JsonParser();
        JsonElement o = parser.parse(response.toString());

//        logger.info("Music json object: " + o.getAsJsonObject());

        // Query URL
        //mNext = "" + o.getAsJsonObject().get("next");
        return o.getAsJsonObject();
    }

    /**
     * Cleans URL of special characters
     * @param url
     * @return
     */
    private String cleanString(String url) {
        List<String> accents = Arrays.asList("È","É","Ê","Ë","Û","Ù","Ï","Î","À","Â","Ô","è","é","ê","ë","û","ù","ï","î","à","â","ô","Ç","ç","Ã","ã","Õ","õ");
        List<String> expected = Arrays.asList("E","E","E","E","U","U","I","I","A","A","O","e","e","e","e","u","u","i","i","a","a","o","C","c","A","a","O","o");

        int acount = 0;

        for (String c : accents) {
            if (url.contains(c)) {
                url = url.replace(c, expected.get(acount));
            }
            acount++;
        }

        return url;
    }

}
