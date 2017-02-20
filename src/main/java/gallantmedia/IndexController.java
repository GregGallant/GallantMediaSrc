package gallantmedia;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ggallant on 2/20/17.
 */
public class IndexController
{

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String contactForm() {
        return("well okay something?");
    }
}
