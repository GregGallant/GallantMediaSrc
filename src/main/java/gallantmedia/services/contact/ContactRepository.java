package gallantmedia.services.contact;

import gallantmedia.models.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long>
{
    List<Contact> findAll();

    Contact findById(ID id);

    @Query(value="SELECT c.email FROM Contact c WHERE c.id = 22")
    public String findName();

}