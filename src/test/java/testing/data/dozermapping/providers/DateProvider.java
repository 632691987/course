package testing.data.dozermapping.providers;

import static java.util.Arrays.asList;

import java.util.Collection;

import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.springframework.stereotype.Component;

import course.dozer.MappingProvider;
import testing.data.dozermapping.convertor.DateStringConvertor;
import testing.data.dozermapping.dtos.date.DateDto1;
import testing.data.dozermapping.dtos.date.DateStringDto;

@Component
public class DateProvider implements MappingProvider {
	@Override
	public Collection<BeanMappingBuilder> getMapperConfigurations() {
		return asList(dateMapping());
	}

	private BeanMappingBuilder dateMapping() {
		return new BeanMappingBuilder() {
			@Override
			protected void configure() {
				mapping(DateStringDto.class, DateDto1.class)
				.fields("dateString", "date", FieldsMappingOptions.customConverter(DateStringConvertor.class));
			}
		};
	}
}
