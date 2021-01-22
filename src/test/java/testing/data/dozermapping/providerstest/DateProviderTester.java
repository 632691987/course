package testing.data.dozermapping.providerstest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

import course.dozer.DozerMapper;
import testing.data.dozermapping.config.DozerMappingConfig;
import testing.data.dozermapping.dtos.date.DateDto1;
import testing.data.dozermapping.dtos.date.DateStringDto;

@Configuration
@Import({ DozerMappingConfig.class })
public class DateProviderTester {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DateProviderTester.class);
		DozerMapper dozerMapper = context.getBean(DozerMapper.class);
		
		dateTest(dozerMapper);
		context.close();
	}
	
	/**
	 * Customer convertor
	 * Provider:  DateProvider
	 */
	private static void dateTest(DozerMapper dozerMapper) {
		DateStringDto dto = new DateStringDto();
		DateDto1 dateDto1 = dozerMapper.map(dto, DateDto1.class);
		
		String dateFormatStr = "yyyy-MM-dd HH:mm:ss";
		DateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
		
		Assert.isTrue(dateFormat.format(dateDto1.getDate()).equalsIgnoreCase(dto.getDateString()));
	}

}
