package course.config;

import java.util.HashSet;
import java.util.Set;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

import course.config.context.ObjectMapperContextResolver;
import course.config.context.ValidationConfigurationContextResolver;
import course.resources.AdminResource;
import course.resources.CollegeResource;
import course.resources.DTOResource;
import course.resources.JmsResource;
import course.resources.JmxResource;
import course.resources.Perf4Resource;
import course.resources.PingResource;
import course.service.AdminService;
import course.service.CollegeService;
import course.service.JMSService;
import course.service.JmxService;
import course.service.Perf4Service;
import course.system.listener.CourseApplicationEventListener;

@Configuration
public class CourseResourceConfig {

	private static final String EXCEPTION_MAPPER_PACKAGE = "course.exceptions.mapper";
	@Autowired
	private AdminService adminService;
	@Autowired
	private CollegeService collegeService;
	@Autowired
	private Perf4Service perf4Service;
	@Autowired
	private JmxService jmxService;
	@Autowired
	private JMSService jmsService;
	@Autowired
	private ObjectMapper objectMapper;
	
	@Bean
	public ResourceConfig resourceConfig() throws Exception {
		ResourceConfig courseResourceConfig = new ResourceConfig();
		courseResourceConfig.setApplicationName("course");
		courseResourceConfig.register(new ObjectMapperContextResolver(objectMapper));
		courseResourceConfig.register(ValidationConfigurationContextResolver.class);
		courseResourceConfig.register(JacksonFeature.class);
		courseResourceConfig.register(MultiPartFeature.class);
		courseResourceConfig.register(CourseApplicationEventListener.class);
		
		courseResourceConfig.property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
		
		// Validation errors to be sent to the client.
		courseResourceConfig.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		
		courseResourceConfig.property(ServerProperties.MONITORING_STATISTICS_MBEANS_ENABLED, true);
		courseResourceConfig.packages(EXCEPTION_MAPPER_PACKAGE);
		
		courseResourceConfig.registerInstances(rsInstance());
		
		return courseResourceConfig;
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
		MappingJackson2HttpMessageConverter convertor = new MappingJackson2HttpMessageConverter();
		convertor.setObjectMapper(objectMapper);
		return convertor;
	}
	
	private Set<Object> rsInstance(){
		Set<Object> instances= new HashSet<>();
		instances.add(pingResource());
		instances.add(DTOResource());
		instances.add(adminResource());
		instances.add(collegeResource());
		instances.add(perf4Resource());
		instances.add(jmxResource());
		instances.add(jmsResource());
		
		return instances;
	}
	
	@Bean
	public PingResource pingResource(){
		return new PingResource();
	}
	
	@Bean
	public DTOResource DTOResource(){
		return new DTOResource();
	}
	
	@Bean
	public AdminResource adminResource(){
		return new AdminResource(adminService);
	}
	
	@Bean
	public CollegeResource collegeResource(){
		return new CollegeResource(collegeService);
	}
	
	@Bean
	public Perf4Resource perf4Resource(){
		return new Perf4Resource(perf4Service);
	}
	
	@Bean
	public JmxResource jmxResource(){
		return new JmxResource(jmxService);
	}
	
	@Bean
	public JmsResource jmsResource(){
		return new JmsResource(jmsService);
	}
}
