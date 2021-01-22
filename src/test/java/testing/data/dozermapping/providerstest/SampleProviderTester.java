package testing.data.dozermapping.providerstest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

import course.dozer.DozerMapper;
import testing.data.dozermapping.config.DozerMappingConfig;
import testing.data.dozermapping.dtos.sample.Sample1;
import testing.data.dozermapping.dtos.sample.Sample2;

@Configuration
@Import({ DozerMappingConfig.class })
public class SampleProviderTester {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SampleProviderTester.class);
		DozerMapper dozerMapper = context.getBean(DozerMapper.class);
		
		sampleTest(dozerMapper);
		context.close();
	}
	
	private static void sampleTest(DozerMapper dozerMapper) {
		Sample1 sample1 = new Sample1();
		sample1.setColumn1("value1");
		sample1.setColumn2("value2");
		
		Sample2 sample2 = dozerMapper.map(sample1, Sample2.class);
		Assert.isTrue(sample2.getColumn1().equalsIgnoreCase(sample1.getColumn1()));
		Assert.isTrue(sample2.getColumn2().equalsIgnoreCase(sample1.getColumn2()));
	}

}
