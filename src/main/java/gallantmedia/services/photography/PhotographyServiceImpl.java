package gallantmedia.services.photography;

import gallantmedia.models.Photography;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class PhotographyServiceImpl implements PhotographyService
{
    private static final Logger log = LoggerFactory.getLogger("PhotographyService");

    //private EntityManager em;

    private PhotographyRepository photographyRepository;

    public List<Photography> findAll() {
        return photographyRepository.findAll();
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

        if (photographyRepository.findName() != null) {
            return photographyRepository.findName();
        }

        return "No data found.";
    }

    public void savePhotography(Photography photography) {
        photographyRepository.save(photography);
    }
}
