package gallantmedia;

import gallantmedia.models.Contact;
import gallantmedia.services.customer.CustomerRepository;
import gallantmedia.services.customer.CustomerService;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import gallantmedia.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class RegisterController
{

    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String registerGet(Model model)
    {
        model.addAttribute("userForm", new Customer());
        return "register";
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerFormSubmit(@Valid @ModelAttribute("userForm") Customer userForm, BindingResult bindingResult)
    {
        customerService.save(userForm);

        return "redirect:/index";
    }

    /**
     * Sets created and updated timestamps
     * @param customer
     * @return
     */
    private Customer setTimestamps(Customer customer)
    {
        Date sqlnow;
        java.util.Date dateStr = null;
        Instant instant = Instant.now();
        Timestamp timestamp = Timestamp.from(instant);
        SimpleDateFormat sformatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


        try {
            dateStr = sformatter.parse(timestamp.toString());
        } catch(ParseException pe) {
            System.out.println("ParseException: " + pe.toString());
        } catch(Exception e) {
            System.out.println("Exception: " + e.toString());
        }

        if (dateStr != null) {
            sqlnow = new java.sql.Date(dateStr.getTime());
            //customer.setCreated(sqlnow);
            //customer.setUpdated(sqlnow);
        }

        return customer;
    }


}
