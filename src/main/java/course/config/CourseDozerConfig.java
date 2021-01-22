package course.config;

import java.util.Set;
import java.util.function.Consumer;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import course.dozer.DozerMapper;
import course.dozer.MappingProvider;

@Configuration
@ComponentScan(CourseDozerConfig.DOZER_PACKAGE)
public class CourseDozerConfig {

	public static final String DOZER_PACKAGE = "course.dozer";

	@Bean
	public DozerMapper dozerMapper(Set<MappingProvider> mapperConfigurations) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapperConfigurations.forEach(configureMapper(mapper));

		return new DozerMapper(mapper);
	}

	private Consumer<MappingProvider> configureMapper(DozerBeanMapper mapper) {
		return mapperConfigurationProvider -> mapperConfigurationProvider.getMapperConfigurations()
				.forEach(mapper::addMapping);
	}
}
