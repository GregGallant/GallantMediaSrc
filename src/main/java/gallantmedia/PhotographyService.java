package gallantmedia;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class PhotographyService
{
    @PersistenceContext
    private EntityManager em;

//    @Transactional
 //   public List<Photography> getAll() {
  //      List<Photography> result = em.createQuery("SELECT p.filename FROM photography p", Photography.class);
   // }
}
