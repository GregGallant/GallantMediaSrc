package gallantmedia.services.customer;

import java.util.*;
import gallantmedia.models.Customer;

public interface CustomerService
{
    public List<Customer> findAll();

    public Customer findUserByEmail(String email);

    public void saveUser(Customer user);
}