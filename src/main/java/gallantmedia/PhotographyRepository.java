package gallantmedia;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PhotographyRepository extends CrudRepository<Photography, Long>
{
    List<Photography> findAll();

    @Query(value="SELECT p.filename FROM Photography p WHERE p.id = 1")
    public String findName();
}
