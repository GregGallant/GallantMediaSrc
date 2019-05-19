package gallantmedia;

import gallantmedia.services.news.Goodnews;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import gallantmedia.models.Contact;

import java.util.List;

import gallantmedia.services.contact.ContactRepository;

import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import gallantmedia.services.news.Article;
import org.springframework.web.servlet.ModelAndView;

/**
 * A kind of vue controller
 */
@RestController
public class NewsAdminController {

    private Goodnews goodnews;

    private ContactRepository contactRepository;

    // Relevancy Notes
    /*
     section_title (when populated, has major names, sometimes empty, make list, run some analytics)
     site_type (news)
     persons/sentiment/name (find relevant names)
     span_score (needs to be 0.0)
    */

    @RequestMapping(value="/newsbuild", method=RequestMethod.GET)
    public ModelAndView newsbuild(Contact contact, BindingResult result, Model model)
    {
        // StringBuilder stringB = new StringBuilder("");

        String bignews;
        Goodnews goodnews = new Goodnews();
        List<Article> listOfArticles = goodnews.getLatestNewsAdmin("US","admin");

        ModelAndView mav = new ModelAndView("newsbuild");

        mav.addObject(listOfArticles);
        model.addAttribute("listOfArticles", listOfArticles);
        return mav;
    }

    @RequestMapping(value="/sportsbuild", method=RequestMethod.GET)
    public ModelAndView newssportsbuild(Contact contact, BindingResult result, Model model)
    {
        //    StringBuilder stringB = new StringBuilder("");


        String bignews;
        Goodnews goodnews = new Goodnews();
        List<Article> listOfArticles = goodnews.getLatestNewsAdmin("sports","admin");

        ModelAndView mav = new ModelAndView("newsbuild");

        mav.addObject(listOfArticles);
        model.addAttribute("listOfArticles", listOfArticles);
        return mav;
    }

    @RequestMapping(value="/entbuild", method=RequestMethod.GET)
    public ModelAndView newsentbuild(Contact contact, BindingResult result, Model model)
    {
        //    StringBuilder stringB = new StringBuilder("");


        String bignews;
        Goodnews goodnews = new Goodnews();
        List<Article> listOfArticles = goodnews.getLatestNewsAdmin("entertainment","admin");

        ModelAndView mav = new ModelAndView("newsbuild");

        mav.addObject(listOfArticles);
        model.addAttribute("listOfArticles", listOfArticles);
        return mav;
    }

    @RequestMapping(value="/techbuild", method=RequestMethod.GET)
    public ModelAndView newstechbuild(Contact contact, BindingResult result, Model model)
    {
        //    StringBuilder stringB = new StringBuilder("");


        String bignews;
        Goodnews goodnews = new Goodnews();
        List<Article> listOfArticles = goodnews.getLatestNewsAdmin("tech","admin");

        ModelAndView mav = new ModelAndView("newsbuild");

        mav.addObject(listOfArticles);
        model.addAttribute("listOfArticles", listOfArticles);
        return mav;
    }





    @RequestMapping(value="/contactAdmin", method=RequestMethod.GET)
    public ModelAndView contactBuild(Contact contact, BindingResult result, Model model)
    {
        ModelAndView mav = new ModelAndView("contactAdmin");
        mav.addObject(contactRepository.findAll());

        return mav;
    }


    @CrossOrigin(origins = "http://news.gallantone.com")
    @RequestMapping(value="/supernews", method=RequestMethod.GET)
    public String adminUSNewsJson()
    {
        String bignews;
        Goodnews goodnews = new Goodnews();
        bignews = goodnews.getLatestNews("US","json");

        return bignews;
    }


    @CrossOrigin(origins = "http://news.gallantone.com")
    @RequestMapping(value="/newsorder", method=RequestMethod.GET)
    public String adminUSNewsOrderJson()
    {
        String bignews;
        Goodnews goodnews = new Goodnews();
        bignews = goodnews.getLatestNews("US","order");

        return bignews;
    }


    @CrossOrigin(origins = "http://news.gallantone.com")
    @RequestMapping(value="/newsanalytics", method=RequestMethod.GET)
    public String adminAnalyticsJson()
    {
        String bignews;
        Goodnews goodnews = new Goodnews();
        bignews = goodnews.getLatestNews("US","json");

        return bignews;
    }
}
