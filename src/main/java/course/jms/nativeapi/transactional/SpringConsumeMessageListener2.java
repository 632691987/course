package course.jms.nativeapi.transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by yan on 2016/3/26.
 */
public class SpringConsumeMessageListener2 implements SessionAwareMessageListener<TextMessage> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Destination destination;

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    @Override
    public void onMessage(TextMessage message, Session session) throws JMSException {
        logger.info("this is for receive and then send to another queue");
    }
}
