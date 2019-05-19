package gallantmedia.services.customer;

import gallantmedia.GallantUserDetailsService;
import gallantmedia.services.customer.CustomerRepository;
import gallantmedia.models.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService
{

    @PersistenceContext
    private EntityManager entityManager;

    private CustomerRepository customerRepository;

    public List<Customer> findAll()
    {
        List<Customer> emptyList = new ArrayList<Customer>();
        return emptyList;
    }

    public void saveUser(Customer user) {}

    public Customer findUserByEmail(String email)
    {
        Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
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
