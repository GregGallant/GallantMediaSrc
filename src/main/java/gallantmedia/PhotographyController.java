package gallantmedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhotographyController
{
    @Autowired
    private PhotographyService photographyService;

    @Autowired
    private PhotographyRepository photographyRepository;

    @RequestMapping("/photography")
    public List<Photography> index() {
        return photographyService.findAll();
    }
}
