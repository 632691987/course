package testing.data.dozermapping.config;

import java.util.Set;
import java.util.function.Consumer;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import course.dozer.DozerMapper;
import course.dozer.MappingProvider;

@Configuration
@ComponentScan({ DozerMappingConfig.PROVIDERS_PACKAGE, DozerMappingConfig.DTO_PACKAGE })
public class DozerMappingConfig {
	static final String DTO_PACKAGE = "testing.data.dozermapping.dtos";
	static final String PROVIDERS_PACKAGE = "testing.data.dozermapping.providers";
	
    @Bean
    public DozerMapper dozerMapper(Set<MappingProvider> mapperConfigurations) {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapperConfigurations.forEach(configureMapper(mapper));

        return new DozerMapper(mapper);
    }
	
    private Consumer<MappingProvider> configureMapper(DozerBeanMapper mapper) {
        return mapperConfigurationProvider -> mapperConfigurationProvider.getMapperConfigurations().forEach(mapper::addMapping);
    }
}
