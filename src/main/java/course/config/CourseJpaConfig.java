package course.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages={CourseJpaConfig.MODEL_PACKAGE})
@EnableJpaRepositories(CourseJpaConfig.REPOSITORY_PACKAGE)
public class CourseJpaConfig {
	public static final String MODEL_PACKAGE      = "course.data.model";
	public static final String REPOSITORY_PACKAGE = "course.data.repository";
}
