package gallantmedia;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ggallant on 2/20/17.
 */
@RestController
public class IndexController
{
    @RequestMapping(value="/contact", method = RequestMethod.POST)
    public String contactForm(HttpServletRequest request, @ModelAttribute Contact contact)
    {
        // stuff into database

        return("well okay " + request.getParameter("lastname") + "?");
    }
}
