package course.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import course.config.properties.CourseJMSConfiguration;
import course.jms.QueueASender;
import course.jms.QueueBSender;
import course.jms.entity.AdminLoginEntity;

@Service
public class JMSService {

	private final String DEST_QUEUE_A = "QueueA";
	private final String DEST_QUEUE_B = "QueueB";
	private final String TOPIC_A = "TopicA";
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private QueueASender queueASender;
	@Autowired
	private QueueBSender queueBSender;
	@Autowired
	private CourseJMSConfiguration jmsConfig;
	
	@JmsListener(destination = DEST_QUEUE_A, selector="sendFrom='student'")
	public void processMessageFromStudent(String message){
		logger.info("Receive and process message from student: " + message);
	}
	
	@JmsListener(destination = DEST_QUEUE_A, selector="sendFrom='teacher'")
	public void processMessageFromTeacher(String message){
		logger.info("Receive and process message from teacher: " + message);
	}
	
	@JmsListener(destination = DEST_QUEUE_B)
	public void processMessageFromQueueB(String message){
		logger.info("Receive and process message from QueueB: " + message);
	}

	@JmsListener(destination = TOPIC_A)
	public void processMessageFromTopicA(String message) {
		logger.info("Receive and process message from TopicA: " + message);
	}
	
	@Retryable(backoff = @Backoff(delay = 500L, multiplier = 2))
	public void sendToQueueA(String toHandler){
		AdminLoginEntity adminLoginEntity = new AdminLoginEntity();
		adminLoginEntity.setAdminName("setAdminName");
		adminLoginEntity.setMaintainField01("setMaintainField01");
		adminLoginEntity.setMaintainField02("setMaintainField02");
		adminLoginEntity.setMessageCode("setMessageCode");
		
		queueASender.sendObjectMessage(adminLoginEntity, toHandler);
	}
	
	@Retryable(backoff = @Backoff(delay = 500L, multiplier = 2))
	public void sendToQueueB(String toHandler){
		AdminLoginEntity adminLoginEntity = new AdminLoginEntity();
		adminLoginEntity.setAdminName("setAdminName");
		adminLoginEntity.setMaintainField01("setMaintainField01");
		adminLoginEntity.setMaintainField02("setMaintainField02");
		adminLoginEntity.setMessageCode("setMessageCode");

		queueBSender.sendObjectMessage(adminLoginEntity, toHandler);
	}

	@Retryable(backoff = @Backoff(delay = 500L, multiplier = 2))
	public void sendToQueueE(String message) {
		queueBSender.sendMessageToQueue(jmsConfig.getQueueE(), message);
	}

}
