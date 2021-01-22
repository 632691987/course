package course.dozer;

import java.util.Collection;

import org.dozer.loader.api.BeanMappingBuilder;

public interface MappingProvider {
	Collection<BeanMappingBuilder> getMapperConfigurations();
}
