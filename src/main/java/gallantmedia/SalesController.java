package gallantmedia;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * A kind of vue controller
 */
@RestController
public class SalesController {

    @RequestMapping(value="/sales", method=RequestMethod.GET)
    public String frameworkIntro() {
        return "";
    }


    @RequestMapping(value="/sales", method=RequestMethod.POST)
    public String frameworkSubmit() {
        return "";
    }

}
