package testing.data.dozermapping.providers;

import static java.util.Arrays.asList;

import java.util.Collection;

import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;

import course.dozer.MappingProvider;
import testing.data.dozermapping.dtos.sample.Sample1;
import testing.data.dozermapping.dtos.sample.Sample2;

@Component
public class SampleProvider implements MappingProvider{

	@Override
	public Collection<BeanMappingBuilder> getMapperConfigurations() {
		return asList(sampleMapping());
	}
	
	private BeanMappingBuilder sampleMapping(){
		return new BeanMappingBuilder(){
			@Override
			protected void configure() {
				mapping(Sample1.class, Sample2.class);
			}
		};
	}

}
