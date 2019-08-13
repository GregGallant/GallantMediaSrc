package gallantmedia.services.music;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

          List<String> musicArr = new ArrayList<>();
          for (JsonElement e : musicAsJEArr) {
              musicArr.add(e.getAsJsonObject().get("id").toString());
              musicArr.add(e.getAsJsonObject().get("name").toString());
          }

          /*
          logger.info("ID: " + musicArr.get(0));
          logger.info("ID: " + musicArr.get(1));
          logger.info("Tracklist: " + musicArr.get(2));
          */

          String artistName = musicArr.get(1).replaceAll(" ", "%20");
          JsonElement albumList = renderAlbumsByArtistName(artistName);
          JsonElement albumListData = albumList.getAsJsonObject().get("data");
          JsonArray albumAsJEArr = albumListData.getAsJsonArray();

          logger.info("Album Listing: " + albumList);

          List<String> albumArr = new ArrayList<>();
          for (JsonElement e : albumAsJEArr) {
               albumArr.add(e.getAsJsonObject().get("id").toString());
               albumArr.add(e.getAsJsonObject().get("cover_medium").toString());
               albumArr.add(e.getAsJsonObject().get("cover_big").toString());
               albumArr.add(e.getAsJsonObject().get("tracklist").toString());
          }

          String tracklistUrl = albumArr.get(3);
         logger.info("Tracklist URL: " + tracklistUrl);
          JsonElement tracklist = renderTracksByAlbum(tracklistUrl);

          logger.info("Tracks Listing: " + tracklist);
          //logger.info("Artist ID Element: " + artist_id_element );
          //musicAsJEArr.get(0).getAsJsonObject
          //logger.info("Tracklist Element: " + tracklist_element );

          // For artists like Captain Beefheart / Captain Beefheart & his magic band, need the next
          // With full album/tracklist

          // Tracklist
          //JsonElement full_tracklist_by_artist = musicDataElement.getAsJsonObject().get("tracklist");
          //logger.info("===> MUSICJSON TRACKLIST : " + full_tracklist_by_artist);

          //int artist_id = artist_id_element.getAsInt();

          //JsonElement albumList = this.renderAlbumsByArtistId(artist_id);

          GsonBuilder builder = new GsonBuilder();
          Gson gson = builder.create();
          String musicString = gson.toJson(musicJson);
          return musicString;
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
        // Get Tracklist from Artist
        // This is the most reliable source for album data
        // Release date is direct album lookup via album id; release as comparable, order filter;
        // This means hash of album/tracklist each; key=album_id; sort=release_date in a set or linked list
        // Convert back to Json and return

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
                url = new URL(mUrl);
            }
            logger.info("URL object: " + url);
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

        logger.info("Music json object: " + o.getAsJsonObject());

        // Query URL
        //mNext = "" + o.getAsJsonObject().get("next");
        return o.getAsJsonObject();
    }

}
