package gallantmedia.services.customer;

import gallantmedia.models.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService
{
    public List<Customer> findAll()
    {
        List<Customer> emptyList = new ArrayList<Customer>();
        return emptyList;
    }

    public Customer findUserByEmail(String email)
    {
        Customer c = new Customer();
        return c;
    }

    public void saveUser(Customer user) {}

}
