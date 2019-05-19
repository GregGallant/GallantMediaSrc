package gallantmedia;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import gallantmedia.services.news.Goodnews;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin(origins = "http://news.gallantone.com")
@CrossOrigin(origins = "http://staging2.gallantone.com")
@RestController
public class NewsController
{
    private Goodnews goodnews;

    @RequestMapping(value="/news", method=RequestMethod.GET)
    public String showNews()
    {
        String bignews = "";
        Goodnews goodnews = new Goodnews();
        bignews = goodnews.getLatestNews("tech", "full");

        return bignews;
    }

    @CrossOrigin(origins = "https://www.gallantone.com")
    @RequestMapping(value="/newslinks", method=RequestMethod.GET)
    public String showNewsLinks()
    {
        String bignews;
        Goodnews goodnews = new Goodnews();
        bignews = goodnews.getLatestNews("tech", "links");

        return bignews;
    }

    @RequestMapping(value="/newsjson", method=RequestMethod.GET)
    public String showNewsJson() {
        String bignews;
        Goodnews goodnews = new Goodnews();
        bignews = goodnews.getLatestNews("tech","json");

        return bignews;
    }

    @RequestMapping(value="/newsusjson", method=RequestMethod.GET)
    public String showUSNewsJson() {
        String bignews;
        Goodnews goodnews = new Goodnews();
        bignews = goodnews.getLatestNews("US","json");

        return bignews;
    }

    // US News
    @RequestMapping(value="/newsus", method=RequestMethod.GET)
    public String showUSNews()
    {
        String bignews = "";
        Goodnews goodnews = new Goodnews();
        bignews = goodnews.getLatestNews("US", "full");

        return bignews;
    }

    @RequestMapping(value="/newsbusinessjson", method=RequestMethod.GET)
    public String showStocksNewsJson() {
        String bignews;
        Goodnews goodnews = new Goodnews();
        bignews = goodnews.getLatestNews("business","json");

        return bignews;
    }

    @RequestMapping(value="/newssportsjson", method=RequestMethod.GET)
    public String showSportsNewsJson() {
        String bignews;
        Goodnews goodnews = new Goodnews();
        bignews = goodnews.getLatestNews("sports","json");

        return bignews;
    }

    @RequestMapping(value="/newsfashionjson", method=RequestMethod.GET)
    public String showFashionNewsJson() {
        String bignews;
        Goodnews goodnews = new Goodnews();
        bignews = goodnews.getLatestNews("fashion","json");

        return bignews;
    }

    @RequestMapping(value="/newsentertainmentjson", method=RequestMethod.GET)
    public String showEntNewsJson() {
        String bignews;
        Goodnews goodnews = new Goodnews();
        bignews = goodnews.getLatestNews("entertainment","json");

        return bignews;
    }
}
