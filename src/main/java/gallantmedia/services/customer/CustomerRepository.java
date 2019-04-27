package gallantmedia.services.customer;

import gallantmedia.models.Customer;
import org.springframework.security.core.userdetails.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import com.sun.xml.bind.v2.model.core.ID;

public interface CustomerRepository extends CrudRepository<Customer, Long>
{
    List<Customer> findAll();

    Customer findUserByEmail(String email);
}
