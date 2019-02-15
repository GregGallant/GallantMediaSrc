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
import java.util.ArrayList;

/**
 * A kind of vue controller
 */
@RestController
public class NewsController {

    private Goodnews goodnews;

    @RequestMapping(value="/newsbuild", method=RequestMethod.GET)
    public String newsScoop()
    {
        Goodnews goodnews = new Goodnews();
        String newTechNews = goodnews.renderLatestNews();
        return newTechNews;
    }

    @RequestMapping(value="/news", method=RequestMethod.GET)
    public String showNews()
    {
        String bignews = "";
        Goodnews goodnews = new Goodnews();
        bignews = goodnews.getLatestTechNews();
        /*
        ArrayList<String> newTechNews = goodnews.getLatestTechNews();

        for(String newsitem : newTechNews)  {
            bignews += "<p>" + newsitem + "</p>";
        }
        */

        return bignews;
    }
}
