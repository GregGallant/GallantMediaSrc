package gallantmedia.services.photography;

import gallantmedia.models.Photography;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotographyRepository extends CrudRepository<Photography, Long>
{
    List<Photography> findAll();

    @Query(value="SELECT p.filename FROM Photography p WHERE p.id = 1")
    public String findName();
}