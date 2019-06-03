package gallantmedia;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ggallant on 2/20/17.
 */
//@CrossOrigin(origins = "https://www.gallantone.com")
@RestController
public class JsonLoginController
{

    @CrossOrigin(origins = "http://staging.gallantone.com")
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginPostInit(Model model) {
        //return new ModelAndView("loginsuccess");
        return "loginsuccess";
    }
}

