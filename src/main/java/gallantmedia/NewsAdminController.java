package gallantmedia;

import gallantmedia.services.news.Goodnews;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import gallantmedia.models.Contact;
import gallantmedia.services.contact.ContactService;
import gallantmedia.services.contact.ContactServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import gallantmedia.services.contact.ContactRepository;

/**
 * A kind of vue controller
 */
@RestController
public class NewsAdminController {

    private Goodnews goodnews;

    @Autowired
    private ContactRepository contactRepository;

    // Relevancy Notes
    /*
     section_title (when populated, has major names, sometimes empty, make list, run some analytics)
     site_type (news)
     persons/sentiment/name (find relevant names)
     span_score (needs to be 0.0)
    */

    @RequestMapping(value="/newsbuild", method=RequestMethod.GET)
    public String newsScoop()
    {
        StringBuilder stringB = new StringBuilder("");

        /*
        Goodnews goodnews = new Goodnews();
        if (goodnews.renderLatestNews()) {
            return "News rendered.";
        }
        */

        List<Contact> contactList = contactRepository.findAll();

        for(Contact contact:contactList) {
            stringB.append(contact.getFirstname());
            stringB.append(contact.getLastname());
            stringB.append(contact.getWebsite());
        }

        return stringB.toString();
    }

    @CrossOrigin(origins = "http://www.gallantone.com")
    @RequestMapping(value="/supernews", method=RequestMethod.GET)
    public String adminUSNewsJson()
    {
        String bignews;
        Goodnews goodnews = new Goodnews();
        bignews = goodnews.getLatestNews("US","json");

        return bignews;
    }


    @CrossOrigin(origins = "http://www.gallantone.com")
    @RequestMapping(value="/newsorder", method=RequestMethod.GET)
    public String adminUSNewsOrderJson()
    {
        String bignews;
        Goodnews goodnews = new Goodnews();
        bignews = goodnews.getLatestNews("US","order");

        return bignews;
    }


    @CrossOrigin(origins = "http://www.gallantone.com")
    @RequestMapping(value="/newsanalytics", method=RequestMethod.GET)
    public String adminAnalyticsJson()
    {
        String bignews;
        Goodnews goodnews = new Goodnews();
        bignews = goodnews.getLatestNews("US","json");

        return bignews;
    }
}
