package gallantmedia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by greg on 7/28/2016.
 */
@Controller
public class AppErrorController implements ErrorController
{
    private static final String PATH = "/error";

    private ErrorAttributes errorAttributes;

    Logger logger = LoggerFactory.getLogger(AppErrorController.class);

    public AppErrorController(ErrorAttributes errorAttributes)
    {
        this.errorAttributes = errorAttributes;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        WebRequest webRequest = new ServletWebRequest(request);
        return this.errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
    }

    @RequestMapping(value=PATH)
    public String error(Model model) {
        logger.info("--=== Error method 1");
        return "error";
    }

    @Override
    public String getErrorPath() {
        logger.info("--=== getErrorPath Method");
        return PATH;
    }
}
