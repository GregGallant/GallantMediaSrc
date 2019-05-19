package gallantmedia;

import gallantmedia.services.photography.PhotographyRepository;
import gallantmedia.services.photography.PhotographyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotographyController
{
    private static final Logger log = LoggerFactory.getLogger("PhotographyController");

    @Autowired
    private PhotographyService photographyService;

    @RequestMapping("/photography")
    public String index() {
        log.info("Photography Mapped, dumping column data: " + photographyService.findName().toString());
        return photographyService.findName().toString();
    }
}
