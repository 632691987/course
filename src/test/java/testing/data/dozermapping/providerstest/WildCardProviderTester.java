package testing.data.dozermapping.providerstest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import course.dozer.DozerMapper;
import junit.framework.Assert;
import testing.data.dozermapping.config.DozerMappingConfig;
import testing.data.dozermapping.dtos.wildcard.WildCardEntity1;
import testing.data.dozermapping.dtos.wildcard.WildCardEntity2;
import testing.data.dozermapping.dtos.wildcard.WildCardEntity3;
import testing.data.dozermapping.dtos.wildcard.WildCardEntity4;

@Configuration
@Import({ DozerMappingConfig.class })
public class WildCardProviderTester {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DateProviderTester.class);
		DozerMapper dozerMapper = context.getBean(DozerMapper.class);
		
		wildCard_TRUE_Test(dozerMapper);
		wildCard_FALSE_Test(dozerMapper);
		context.close();
	}
	
	private static void wildCard_TRUE_Test(DozerMapper dozerMapper) {
		WildCardEntity1 wildCardEntity1 = new WildCardEntity1();
		wildCardEntity1.setValue1("value1");
		wildCardEntity1.setValue2("value2");
		wildCardEntity1.setValue3("value3");
		
		
		WildCardEntity2 wildCardEntity2 = dozerMapper.map(wildCardEntity1, WildCardEntity2.class);
		
		Assert.assertEquals(wildCardEntity1.getValue1(), wildCardEntity2.getValue1());
		Assert.assertEquals(wildCardEntity1.getValue2(), wildCardEntity2.getValue2());
		Assert.assertEquals(wildCardEntity1.getValue3(), wildCardEntity2.getValue3());
	}
	
	private static void wildCard_FALSE_Test(DozerMapper dozerMapper) {
		WildCardEntity3 wildCardEntity3 = new WildCardEntity3();
		wildCardEntity3.setValue1("value1");
		wildCardEntity3.setValue2("value2");
		wildCardEntity3.setValue3("value3");
		
		
		WildCardEntity4 wildCardEntity4 = dozerMapper.map(wildCardEntity3, WildCardEntity4.class);
		
		Assert.assertEquals(wildCardEntity3.getValue1(), wildCardEntity4.getValue1());
		Assert.assertEquals(wildCardEntity3.getValue2(), wildCardEntity4.getValue2());
		Assert.assertEquals(wildCardEntity3.getValue3(), wildCardEntity4.getValue3());
		
		System.out.println(wildCardEntity4.getValue1());
		System.out.println(wildCardEntity4.getValue2());
		System.out.println(wildCardEntity4.getValue3());
	}
}
