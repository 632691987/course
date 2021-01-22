package course.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;

import course.config.jms.hornetq.ConnectionConfig;
import course.config.jms.hornetq.SpringConfig;
import course.config.properties.CourseJMSConfiguration;

@EnableJms
@Configuration
@EnableConfigurationProperties({CourseJMSConfiguration.class})
@Import({ConnectionConfig.class, SpringConfig.class})
@ComponentScan(CourseJMSConfig.COMPONENT_PACKAGE)
public class CourseJMSConfig {
	public static final String COMPONENT_PACKAGE = "course.jms";
}
