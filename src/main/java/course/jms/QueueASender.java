package course.jms;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;

import course.jms.entity.AdminLoginEntity;

@Component
public class QueueASender {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private JmsTemplate jmsTemplate;
	
	private Queue Destination;
	
	@Autowired
	public QueueASender(@Qualifier("jmsTemplate") JmsTemplate jmsTemplate, @Qualifier("QueueA") Queue QueueA) {
		this.jmsTemplate = jmsTemplate;
		this.Destination = QueueA;
		jmsTemplate.setDefaultDestination(QueueA);
		jmsTemplate.setMessageConverter(new SimpleMessageConverter());
	}
	
	public void sendObjectMessage(AdminLoginEntity adminLoginEntity, String toHandler){
		logger.info("About to send object message");
		jmsTemplate.convertAndSend( Destination, new Gson().toJson(adminLoginEntity), new MessagePostProcessor(){
			@Override
			public Message postProcessMessage(Message message) throws JMSException {
				Calendar calendar = Calendar.getInstance();
				DateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
				
				message.setStringProperty("sendFrom", toHandler);
				message.setJMSCorrelationID(df.format(calendar.getTime()));
				return message;
			}
		});
		logger.info("Object message sent");
	}
	
}
