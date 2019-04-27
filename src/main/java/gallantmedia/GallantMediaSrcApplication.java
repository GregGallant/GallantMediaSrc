package gallantmedia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;


@SpringBootApplication
public class GallantMediaSrcApplication extends SpringBootServletInitializer
{
    private static final Logger log = LoggerFactory.getLogger("PhotographyRepository");

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GallantMediaSrcApplication.class);
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(GallantMediaSrcApplication.class, args);
        log.info("== GallantMedia Source Application Running ==");
		/* If you ever want bean names and definitions, I guess...
			String[] beanNames = ctx.getBeanDefinitionNames();
		*/
	}

}
