package testing.data.dozermapping.providers;

import java.util.Arrays;
import java.util.Collection;

import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

import course.dozer.MappingProvider;
import testing.data.dozermapping.dtos.wildcard.WildCardEntity1;
import testing.data.dozermapping.dtos.wildcard.WildCardEntity2;
import testing.data.dozermapping.dtos.wildcard.WildCardEntity3;
import testing.data.dozermapping.dtos.wildcard.WildCardEntity4;

public class WildCardProvider implements MappingProvider{

	@Override
	public Collection<BeanMappingBuilder> getMapperConfigurations() {
		return Arrays.asList(mapEntity122(),mapEntity324());
	}
	
	
	private BeanMappingBuilder mapEntity122(){
		return new BeanMappingBuilder(){
			@Override
			protected void configure() {
				mapping(WildCardEntity1.class, WildCardEntity2.class, TypeMappingOptions.wildcard(true))
				.fields("value1", "value1");
			}
		};
	}
	
	private BeanMappingBuilder mapEntity324(){
		return new BeanMappingBuilder(){
			@Override
			protected void configure() {
				mapping(WildCardEntity3.class, WildCardEntity4.class, TypeMappingOptions.wildcard(false))
				.fields("value1", "value1");
			}
		};
	}

}
