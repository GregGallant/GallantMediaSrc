package gallantmedia;

import gallantmedia.models.Contact;
import gallantmedia.services.contact.ContactRepository;
import gallantmedia.services.contact.ContactService;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ggallant on 2/20/17.
 */
//@CrossOrigin(origins = "https://www.gallantone.com")
@Controller
@CrossOrigin(origins = "https://www.gallantone.com")
public class LoginController
{

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String loginGetInit(Model model) {
        //return new ModelAndView("login");
        return "login";
    }

}

