package gallantmedia.services.music;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Map;

import com.google.gson.JsonElement;
import gallantmedia.services.news.Goodnews;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Goodmusic {

    private String mNext;

    /**
     * @param artist_name
     * @return
     */
    public String renderArtist(String artist_name)
    {
          String artistUrl = "https://api.deezer.com/search/artist/?q="+artist_name+"&index=0&limit=1";
          JsonElement musicJson  = this.getApiResponse(artistUrl);

          GsonBuilder builder = new GsonBuilder();
          Gson gson = builder.create();
          String musicString = gson.toJson(musicJson);
          return musicString;
    }


    public String renderAlbums(int artist_id)
    {
        // Need to get the artist Id from "musicString" json
        // Make new call to album list
    }

    public String renderTrakcs(int album_id)
    {
        // Need to get the album id from each album json
        // Render tracklist link to be handled by reactjs
    }


    /**
     *
     * @param mUrl Server URL
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    protected JsonElement getApiResponse(String mUrl)
    {
        Logger logger = LoggerFactory.getLogger(Goodmusic.class);

        HttpURLConnection connection = null;
        URL url = null;
        StringBuilder response = new StringBuilder();
        InputStream is = null;
        BufferedReader rd = null;

        try {
           url = new URL(mUrl);
           connection = (HttpURLConnection) url.openConnection();
           connection.setRequestMethod("GET");
           connection.setDoOutput(true);
        } catch(ProtocolException pe) {
            logger.error("PE Exception: " + pe.getMessage());
        } catch(IOException ie) {
            logger.error("IO Exception: " + ie.getMessage());
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
            logger.error("IO Exception: " + ie.getMessage());
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
