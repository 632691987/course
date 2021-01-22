package course.jms.nativeapi.transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class SpringConsumeMessageListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Destination destination;

    private int count = 0;

    public SpringConsumeMessageListener() {
        count = 0;
    }

    public SpringConsumeMessageListener(Destination destination) {
        count = 0;
        this.destination = destination;
    }

    @Override
    public void onMessage(Message message) {
        logger.info("Begin to process message");
        TextMessage textMsg = (TextMessage) message;
        try {
            logger.info("JMS message count = {}, content = {}", count, textMsg.getText());
            if (count < 2) {
                count++;
                throw new RuntimeException("count still not to 3");
            }
        } catch (JMSException e) {
            logger.info("SpringConsumeMessageListener receive message error, count = {}", count);
        }
        logger.info("Receive and process message successfully");
    }
}
