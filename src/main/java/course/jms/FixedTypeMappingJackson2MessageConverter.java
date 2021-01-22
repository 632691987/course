package course.jms;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.SimpleType;

public class FixedTypeMappingJackson2MessageConverter extends MappingJackson2MessageConverter {
	
	private final JavaType messageJavaType;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public FixedTypeMappingJackson2MessageConverter(Class<?> messageClass) {
		this.messageJavaType = SimpleType.construct(messageClass);
	}

	@Override
	protected JavaType getJavaTypeForMessage(Message message) throws JMSException {
		return messageJavaType;
	}

	@Override
	protected Object convertFromTextMessage(TextMessage message, JavaType targetJavaType)
			throws JMSException, IOException {
		logger.trace("operation=parse rawMessage={}", message.getText());
		return super.convertFromTextMessage(message, targetJavaType);
	}
}