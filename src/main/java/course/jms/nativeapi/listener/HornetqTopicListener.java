package course.jms.nativeapi.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by yan on 2016/3/19.
 */
@Component(value = "topicListener")
public class HornetqTopicListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            logger.info("HornetqTopicListener receives messages, content = [{}]", textMessage.getText());
        } catch (JMSException e) {
            logger.error("HornetqTopicListener error occurs when receiving message, error message = {}", e.getMessage());
        }
    }
}
