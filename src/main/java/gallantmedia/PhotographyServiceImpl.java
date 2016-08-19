package gallantmedia;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class PhotographyServiceImpl implements PhotographyService
{
    private EntityManager em;

    @Autowired
    private PhotographyRepository photographyRepository;

    public List<Photography> findAll() {
        return photographyRepository.findAll();
    }


    public String findName() {
        Query q = em.createNativeQuery("SELECT filename FROM photography LIMIT 1");
        Object filename = q.getSingleResult();
        return filename.toString();
    }

    public void savePhotography(Photography photography) {
        photographyRepository.save(photography);
    }
}
