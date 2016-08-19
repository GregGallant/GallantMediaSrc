package gallantmedia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhotographyController
{
    private static final Logger log = LoggerFactory.getLogger("PhotographyController");

    @Autowired
    private PhotographyService photographyService;

    @Autowired
    private PhotographyRepository photographyRepository;

    @RequestMapping("/photography")
    //public List<Photography> index() {
    public String index() {
        return photographyService.findName();
    }
}
