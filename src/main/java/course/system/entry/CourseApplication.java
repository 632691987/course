package course.system.entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import course.config.CourseAopConfig;
import course.config.CourseCacheConfig;
import course.config.CourseDozerConfig;
import course.config.CourseIntegrationConfig;
import course.config.CourseJMSConfig;
import course.config.CourseJmxConfig;
import course.config.CourseJpaConfig;
import course.config.CourseResourceConfig;
import course.config.CourseSecurityConfig;
import course.config.CourseServerConfig;
import course.config.CourseServiceConfig;
import course.system.initializer.CourseApplicationContextInitializer;

@EnableAutoConfiguration(exclude = {// all comes from //spring-boot-autoconfigure
		SecurityAutoConfiguration.class,
		ErrorMvcAutoConfiguration.class,
		WebMvcAutoConfiguration.class,
		DispatcherServletAutoConfiguration.class
})
@Import({
	CourseAopConfig.class,
	CourseCacheConfig.class,
	CourseJpaConfig.class,
	CourseServiceConfig.class, 
	CourseServerConfig.class, 
	CourseResourceConfig.class, 
	CourseSecurityConfig.class, 
	CourseJmxConfig.class,
	CourseDozerConfig.class,
	CourseJMSConfig.class,
	CourseIntegrationConfig.class})
@EnableAsync
public class CourseApplication {
	
	final static Logger logger = LoggerFactory.getLogger(CourseApplication.class);
	
	public static void main(String[] args) {
		//To look if could use servlet inilizer
		SpringApplicationBuilder builder = new SpringApplicationBuilder(CourseApplication.class);
		builder.bannerMode(Mode.OFF);
		builder.initializers(new CourseApplicationContextInitializer());
		builder.run(args);
	}
	
}
