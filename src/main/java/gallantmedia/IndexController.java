package gallantmedia;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    @RequestMapping(value="/contact", method = RequestMethod.POST)
    public String contactForm(HttpServletRequest request, @Valid @ModelAttribute Contact contact, BindingResult bindingResult)
    {
        String contactReturn;
        String email;
        String firstname;
        String lastname;
        String description;
        String company;
        String errMsg;

        if (bindingResult.hasErrors()) {
            List allErrors = bindingResult.getAllErrors();
            //String errMsg = "0: " + allErrors.get(0).toString();

          //  String[] errMsg = bindingResult.getSuppressedFields();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            FieldError fe = bindingResult.getFieldError();
            String efield = fe.getField();

            return efield;
        }

        email = request.getParameter("email");
        firstname = request.getParameter("firstname");
        lastname = request.getParameter("lastname");
        description = request.getParameter("description");
        company = request.getParameter("company");


        contactReturn = "Thank you for writing.  Your details are as follows:";
        contactReturn += email + "<br/>\n" + firstname + "<br/>\n" + lastname + "<br/>\n" + description + "<br/>\n" + company;
        contactReturn += "\n\n<div><strong>Thank you for your time.</strong></div>";

        ZoneId zid = ZoneId.of("America/New_York");
        ZonedDateTime timenow = ZonedDateTime.now(zid);

        /*
        timenow = LocalDateTime.parse(timenow.format(dtf));
        */
        //2015-03-22 00:00:00

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM:SS");
        String date = timenow.toString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // your template here
        Date sqlnow;

        try {
            java.util.Date dateStr = formatter.parse(date);
            sqlnow = new java.sql.Date(dateStr.getTime());

            // stuff into database
            contact.setCreated(sqlnow);
            contact.setUpdated(sqlnow);
            contact.setEmail(email);
            contact.setFirstname(firstname);
            contact.setLastname(lastname);
            contact.setDescription(description);
            contact.setWebsite(company);
            contactService.saveContact(contact);

        } catch(ParseException pe) {
            System.out.println("ParseException: " + pe.toString());
        } catch(Exception e) {
            System.out.println("Exception: " + e.toString());
        }

        //contactRepository.save(contact);

        return(contactReturn);
    }
}
