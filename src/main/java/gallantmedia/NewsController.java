package gallantmedia;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.webhoseio.sdk.WebhoseIOClient;
import com.google.gson.JsonElement;
import java.util.Map;
import java.util.HashMap;
import java.net.URISyntaxException;
import java.io.IOException;
import gallantmedia.services.news.Goodnews;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;

/**
 * A kind of vue controller
 */
@RestController
public class NewsController {

    private Goodnews goodnews;

    @RequestMapping(value="/news", method=RequestMethod.GET)
    public String newsScoop()
    {

        Goodnews goodnews = new Goodnews();
        String newTechNews = goodnews.getLatestNews();

        /* Check latest timestamps */
       // Date newsdate = goodnews.checkLatestNews();

        // Check if its old news
        //if (newsdate.getTime() > 0f) {
            // old news
            //String newGeneralNews = goodnews.getLatestGeneralNews();
        //} else {
            // Still fresh news
            // Read from r/w news templates in static

            // open general.news, tech.news

            // return the news as json string

        //}


/*
        JsonElement result;

        // Create a WebhoseIOClient instance

        WebhoseIOClient webhoseClient = WebhoseIOClient.getInstance("a3fedef2-8667-4555-a4f1-d31a98f1c5e6");

        Map<String, String> queries = new HashMap<String, String>();
        queries.put("q", "performance_score:>0 (title:\"United States\" OR title:US)");
        queries.put("sort", "crawled");

        // Fetch query result
        try {
            result = webhoseClient.query("filterWebContent", queries);
        } catch(URISyntaxException uri) {
            result = null;
        } catch(IOException uri) {
            result = null;
            return "";
        }

        if (result != null) {
            System.out.println(result.getAsJsonObject().get("totalResults"));     // Print posts count
            return result.getAsJsonObject().toString();
        }
*/
        return newTechNews;
    }
}
