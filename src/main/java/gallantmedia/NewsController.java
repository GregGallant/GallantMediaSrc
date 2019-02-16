package gallantmedia;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import gallantmedia.services.news.Goodnews;
import org.springframework.web.bind.annotation.CrossOrigin;

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
        if (goodnews.renderLatestNews()) {
            return "News rendered.";
        }

        return "No news rendering necessary.";
    }

    @CrossOrigin(origins = "http://www.gallantone.com")
    @RequestMapping(value="/news", method=RequestMethod.GET)
    public String showNews()
    {
        String bignews = "";
        Goodnews goodnews = new Goodnews();
        bignews = goodnews.getLatestNews("tech", "full");

        return bignews;
    }

    @CrossOrigin(origins = "http://www.gallantone.com")
    @RequestMapping(value="/newslinks", method=RequestMethod.GET)
    public String showNewsLinks()
    {
        String bignews;
        Goodnews goodnews = new Goodnews();
        bignews = goodnews.getLatestNews("tech", "links");

        return bignews;
    }

    /* TODO: Return JSON only
    CrossOrigin(origins = "http://www.gallantone.com")
    RequestMapping(value="/newsjson", method=RequestMethod.GET)
    public String showNewsJson() {
        String bignews;
        Goodnews goodnews = new Goodnews();
        bignews = goodnews.getLatestTechNews("links");

        return bignews;

    }
    */
}
