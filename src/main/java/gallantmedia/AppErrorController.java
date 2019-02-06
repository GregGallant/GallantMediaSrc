package gallantmedia;

import jdk.nashorn.internal.objects.annotations.Constructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

/**
 * Created by greg on 7/28/2016.
 */
@Controller
public class AppErrorController implements ErrorController
{
    private static final String PATH = "/error";

    @RequestMapping(value=PATH)
    public String error() {
        return "error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
