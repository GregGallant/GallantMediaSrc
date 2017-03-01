package gallantmedia.services.contact;

import gallantmedia.models.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService
{
    private static final Logger log = LoggerFactory.getLogger("ContactService");

    private EntityManager em;

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public String findName() {
        /**
         * trial 1
         * createNativeQuery style
         Query q = em.createNativeQuery("SELECT filename FROM photography LIMIT 1");
         Object filename = q.getSingleResult();
        */

        /**
         * Trial 2
         * Query Annotation
         */

        //log.info("Trying to reach data: " + filename.toString());

        if (contactRepository.findName() != null) {
            return contactRepository.findName();
        }

        return "No data found.";
    }

    public void saveContact(Contact contact) {
        try {
            contactRepository.save(contact);
        } catch(Exception e) {
            // do something
            System.out.println("Exception thrown: " + e.toString());
        }
    }
}
