package course.config.jms.hornetq;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.fasterxml.jackson.databind.ObjectMapper;

import course.config.properties.CourseJMSConfiguration;
import course.jms.FixedTypeMappingJackson2MessageConverter;
import course.jms.entity.AdminLoginEntity;

@Configuration
public class SpringConfig {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private CourseJMSConfiguration jmsConfig;

	@Autowired
	private ConnectionFactory connectionFactory;

	@Bean
	public DefaultMessageListenerContainer defaultMessageListenerContainer(@Qualifier("QueueE") Queue QueueE) {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setDestination(QueueE);
		container.setSessionTransacted(true);
		return container;
	}

	@Bean(name = "jmsTemplate")
	public JmsTemplate jmsTemplate(){
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory);
		jmsTemplate.setReceiveTimeout(jmsConfig.getTimeout());
		jmsTemplate.setMessageConverter(mappingJackson2MessageConverter());
		jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
		jmsTemplate.setTimeToLive(5000);
		return jmsTemplate;
	}
	
	@Bean
	public JmsMessagingTemplate jmsMessagingTemplate(){
		JmsMessagingTemplate jmsMessagingTemplate = new JmsMessagingTemplate();
		jmsMessagingTemplate.setConnectionFactory(connectionFactory);
		jmsMessagingTemplate.setJmsTemplate(jmsTemplate());
		jmsMessagingTemplate.setJmsMessageConverter(mappingJackson2MessageConverter());
		jmsMessagingTemplate.setDefaultDestinationName(jmsConfig.getQueueB());
		return jmsMessagingTemplate;
	}

	@Bean(name = "courseJmsSendTemplate")
	public JmsTemplate courseJmsSendTemplate() {
		final JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setMessageConverter(jsonOutboundMessageConverter());
		return jmsTemplate;
	}

	@Bean(name = "courseJmsReceiveTemplate")
	public JmsTemplate courseJmsReceiveTemplate() {
		final JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setMessageConverter(jsonInboundMessageConverter(AdminLoginEntity.class));
		jmsTemplate.setReceiveTimeout(1000l);
		return jmsTemplate;
	}

	private MappingJackson2MessageConverter jsonOutboundMessageConverter() {
		final MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
		messageConverter.setTargetType(MessageType.TEXT);
		messageConverter.setObjectMapper(objectMapper);
		return messageConverter;
	}

	private MappingJackson2MessageConverter jsonInboundMessageConverter(Class<?> dtoClass) {
		final FixedTypeMappingJackson2MessageConverter messageConverter = new FixedTypeMappingJackson2MessageConverter(dtoClass);
		messageConverter.setObjectMapper(objectMapper);
		return messageConverter;
	}
	
	private MappingJackson2MessageConverter mappingJackson2MessageConverter(){
		MappingJackson2MessageConverter convetor = new MappingJackson2MessageConverter();
		convetor.setObjectMapper(objectMapper);
		convetor.setTargetType(MessageType.TEXT);
		return convetor;
	}
}
