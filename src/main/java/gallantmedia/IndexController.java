package gallantmedia;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping(value="/contact", method = RequestMethod.POST)
    public String contactForm(HttpServletRequest request, @ModelAttribute Contact contact)
    {
        String email;
        String firstname;
        String lastname;
        String details;
        String company;

        String contactReturn = "";

        email = request.getParameter("email");
        firstname = request.getParameter("firstname");
        lastname = request.getParameter("lastname");
        details = request.getParameter("details");
        company = request.getParameter("company");

        contactReturn = "Thank you for writing.  Your details are as follows:";
        contactReturn += email + "<br/>\n" + firstname + "<br/>\n" + lastname + "<br/>\n" + details + "<br/>\n" + company;
        contactReturn += "\n\n<div><strong>Thank you for your time.</strong></div>";

        // stuff into database
        contact.setEmail(email);
        contact.setFirstname(firstname);
        contact.setLastname(lastname);
        contact.setDescription(details);
        contact.setWebsite(company);

        contactService.saveContact(contact);
        //contactRepository.save(contact);

        return(contactReturn);
    }
}
