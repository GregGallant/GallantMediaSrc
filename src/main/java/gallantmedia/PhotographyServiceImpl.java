package gallantmedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotographyServiceImpl implements PhotographyService
{
    @Autowired
    private PhotographyRepository photographyRepository;

    public List<Photography> findAll() {
        return photographyRepository.findAll();
    }

    public void savePhotography(Photography photography) {
        photographyRepository.save(photography);
    }
}
