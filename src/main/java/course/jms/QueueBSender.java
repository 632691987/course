package course.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

import course.jms.entity.AdminLoginEntity;

@Component
public class QueueBSender {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	private Queue Destination;
	
	@Autowired
	public QueueBSender(JmsMessagingTemplate jmsMessagingTemplate, @Qualifier("QueueB") Queue QueueB){
		this.jmsMessagingTemplate = jmsMessagingTemplate;
		this.Destination = QueueB;
		jmsMessagingTemplate.setDefaultDestination(QueueB);
	}
	
	public void sendObjectMessage(AdminLoginEntity adminLoginEntity, String toHandler){
		logger.info("About to send object message");
		jmsMessagingTemplate.convertAndSend(adminLoginEntity);
//		jmsMessagingTemplate.convertAndSend( "QueueB", adminLoginEntity, new MessagePostProcessor(){
//			@Override
//			public Message postProcessMessage(Message message) throws JMSException {
//				Calendar calendar = Calendar.getInstance();
//				DateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
//				
//				message.setStringProperty("sendFrom", toHandler);
//				message.setJMSCorrelationID(df.format(calendar.getTime()));
//				return message;
//			}
//		});
		logger.info("Object message sent");
	}

	public void sendMessageToQueue(String destination, String message) {
		jmsMessagingTemplate.convertAndSend(destination, message);
	}
	
}
