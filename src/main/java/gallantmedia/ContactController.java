package gallantmedia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gallantmedia.models.Contact;
import gallantmedia.services.contact.ContactRepository;
import gallantmedia.services.contact.ContactService;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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

@RestController
public class ContactController
{
    Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private ContactService contactService;

    private ContactRepository contactRepository;

    @RequestMapping(value="/contact", method = RequestMethod.GET)
    public String contactInit(HttpServletRequest request) {
        return ("{error: jsp}");
    }

    @CrossOrigin(origins = "https://www.gallantone.com")
    @RequestMapping(value="/contact", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public String contactForm(@RequestBody Contact contact)
    {
        logger.info("User trying to contact is: " + contact.getEmail());
        /*
        String errorJson;
        ObjectMapper om = new ObjectMapper();

        // Handle Errors
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            try {
                errorJson = om.writeValueAsString(fieldErrors);
                return errorJson;
            } catch (JsonGenerationException e) {
                e.printStackTrace();
                System.out.println("jsme01");
            } catch (JsonMappingException e) {
                System.out.println("jsme");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("jsme02");
                e.printStackTrace();
            }
        }
        */

        contact.onCreate();
        contact.onUpdate();

        // Set created/updated
        //contact = this.setTimestamps(contact);

        // Save
        try {
            contactService.save(contact);
        } catch(Exception e) {
            return "Exception thrown : " + e.toString();
        }

        Gson gson = new GsonBuilder().create();
        String contactJson = gson.toJson(contact);

        return(contactJson);
    }
}
