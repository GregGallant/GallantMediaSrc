package gallantmedia;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by greg on 7/27/2016.
 */
@RestController
public class HelloController
{
   @RequestMapping("/hello")
   public String index() {
      return "Greetings.";
   }
}
