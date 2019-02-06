package gallantmedia;

import gallantmedia.models.Contact;
import gallantmedia.services.contact.ContactRepository;
import gallantmedia.services.contact.ContactService;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Created by ggallant on 2/20/17.
 */
@RestController
public class IndexController
{

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping(value="/contact", method = RequestMethod.GET)
    public String contactInit(HttpServletRequest request) {
        return ("{error: jsp}");
    }


    @RequestMapping(value="/contact", method = RequestMethod.POST)
    public String contactForm(HttpServletRequest request, @Valid @ModelAttribute Contact contact, BindingResult bindingResult)
    {
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

        // Get contact map as set
        Map<String, String[]> contactMap = request.getParameterMap();
        Set contactSet = contactMap.entrySet();

        // Build contact obj and return  Json
        String contactJson = contact.buildContact(contactSet);

        // Set created/updated
        contact = this.setTimestamps(contact);

        // Save
        contactRepository.save(contact);

        return(contactJson);
    }

    /**
     * Sets created and updated timestamps
     * @param contact
     * @return
     */
    private Contact setTimestamps(Contact contact)
    {
        ZoneId zid = ZoneId.of("America/New_York");
        ZonedDateTime timenow = ZonedDateTime.now(zid);

        String date = timenow.toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // your template here
        Date sqlnow;
        java.util.Date dateStr = null;

        try {
            dateStr = formatter.parse(date);
        } catch(ParseException pe) {
            System.out.println("ParseException: " + pe.toString());
        } catch(Exception e) {
            System.out.println("Exception: " + e.toString());
        }

        if (dateStr != null) {
            sqlnow = new java.sql.Date(dateStr.getTime());
            contact.setCreated(sqlnow);
            contact.setUpdated(sqlnow);
        }

        return contact;
    }

}
