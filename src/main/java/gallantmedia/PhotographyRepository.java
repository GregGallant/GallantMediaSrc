package gallantmedia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PhotographyRepository extends CrudRepository<Photography, Long>
{
    List<Photography> findAll();

}
