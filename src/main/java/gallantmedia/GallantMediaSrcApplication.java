package gallantmedia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.util.Arrays;

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

		System.out.println("Spring beans again.  Rename beans.");

        log.info("Gallantmedia Application Running");

		String[] beanNames = ctx.getBeanDefinitionNames();

		Arrays.sort(beanNames);

		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}
}
