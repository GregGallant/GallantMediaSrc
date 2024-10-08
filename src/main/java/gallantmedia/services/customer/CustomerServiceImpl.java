package gallantmedia.services.customer;

import gallantmedia.GallantUserDetailsService;
import gallantmedia.services.customer.CustomerRepository;
import gallantmedia.models.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService
{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    public List<Customer> findAll()
    {
        List<Customer> emptyList = new ArrayList<Customer>();
        return emptyList;
    }

    public void save(Customer user) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.onCreate();
        user.onUpdate();
        logger.info("=====>>>> User to be saved: " + user.getEmail());
        logger.info("=====>>>> User to be saved: " + user.getPassword());
        logger.info("=====>>>> User to be saved: " + user.getFirstName());
        logger.info("=====>>>> User to be saved: " + user.getLastName());
        user.setStatus(1);
        user.setRole("[\"ROLE_USER\"]"); // TODO: Handle roles
        //user.setRoles(new HashSet<>(roleRepository.findAll()));
        customerRepository.save(user);
    }

    public Customer findUserByEmail(String email)
    {
        logger.info("====D Logger customerservice impl called");
        /*
        Customer c = new Customer();
        c.setFirstName("Greg");
        c.setEmail("ggallant");
        c.setPassword("testing");
        return c;
        */
        return customerRepository.findUserByEmail(email);
    }
}
