package gallantmedia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gallantmedia.models.Contact;
import gallantmedia.services.customer.CustomerRepository;
import gallantmedia.services.customer.CustomerService;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import gallantmedia.models.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.json.simple.parser.JSONParser;

@Controller
public class RegisterController
{

    Logger logger = LoggerFactory.getLogger(RegisterController.class);
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @CrossOrigin(origins = "http://staging.gallantone.com")
    @RequestMapping(value="/register", method= RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String registerGet(
            @RequestParam Map<String, String> reg)
    {
        //public String registerGet(Model model)
        //model.addAttribute("userForm", new Customer());

        return "register";
    }

    @CrossOrigin(origins = "http://staging.gallantone.com")
    @RequestMapping(value="/register", method=RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public String registerFormSubmit(
            @RequestBody Customer reg
    )
    {
        logger.info("User trying to register is: " + reg.getEmail());
        reg.onCreate();
        reg.onUpdate();

        try {
            customerService.save(reg);
        } catch(Exception e) {
            return "Exception thrown: " + e.toString();
        }

        Gson gson = new GsonBuilder().create();
        String userFormJson = gson.toJson(reg);
        if (userFormJson != null) {
            return userFormJson;
        }

        return "No data found from returned json";
        //return "redirect:/index";

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
