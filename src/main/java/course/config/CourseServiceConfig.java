package course.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(CourseServiceConfig.SERVICE_PACKAGE)
public class CourseServiceConfig {
	public static final String SERVICE_PACKAGE = "course.service";
}
