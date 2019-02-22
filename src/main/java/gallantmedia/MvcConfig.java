package gallantmedia;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MvcConfig implements WebMvcConfigurer
{
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/loginsuccess").setViewName("loginsuccess");
        registry.addViewController("/newsbuild").setViewName("newsbuild");
        registry.addViewController("/newsanalytics").setViewName("newsanalytics");
    }
}