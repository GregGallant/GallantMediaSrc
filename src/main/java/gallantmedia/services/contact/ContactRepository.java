package gallantmedia.services.contact;

import gallantmedia.models.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long>
{
    List<Contact> findAll();

    @Query(value="SELECT c.email FROM Contact c WHERE c.id = 1")
    public String findName();

}