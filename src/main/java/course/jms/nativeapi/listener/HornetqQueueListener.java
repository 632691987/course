package course.jms.nativeapi.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by yan on 2016/3/19.
 */
public class HornetqQueueListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String toClient;

    public HornetqQueueListener(String toClient) {
        this.toClient = toClient;
    }

    @Override
    public void onMessage(Message message) {
        try {
            logger.info("To {}, QeueuCListnener received messages, with content:[{}]", toClient, ((TextMessage) message).getText());
        } catch (JMSException e) {
            logger.error("Error occurs when HornetqQueueListener receiving message: {}", e.getMessage());
        }
    }
}
