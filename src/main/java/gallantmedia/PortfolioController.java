package gallantmedia;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortfolioController
{
    @RequestMapping("/portfolio")
    public String index() {
        return "Portfolio Endpoint.";
    }
}
