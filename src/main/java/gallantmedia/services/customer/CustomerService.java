package gallantmedia.services.customer;

import java.util.*;
import gallantmedia.models.Customer;

public interface CustomerService
{
    public List<Customer> findAll();

    public void saveUser(Customer user);

    public Customer findUserByEmail(String email);

}