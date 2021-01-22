package course.jms.nativeapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.jms.CompletionListener;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by yan on 2016/3/19.
 */
@Component
public class CourseJMSSender {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ConnectionFactory connectionFactory = null;

    private Connection connection = null;

    private Session session = null;

    private MessageProducer messageProducer = null;

    private CompletionListener completionListener = null;

    @Autowired
    public CourseJMSSender(
            ConnectionFactory connectionFactory,
            @Qualifier("queueCompletionListener") CompletionListener completionListener,
            @Qualifier("QueueC") Queue queueC
    ) throws JMSException {
        this.connectionFactory = connectionFactory;
        this.completionListener = completionListener;
        renconnect(queueC);
    }

    private void renconnect(Queue initQ) throws JMSException {
        connection = connectionFactory.createConnection();
        session = connection.createSession();
        messageProducer = session.createProducer(initQ);
    }

    public void sentMessage(String strMessage, String toGroup) throws JMSException {
        TextMessage message = session.createTextMessage(strMessage);
        message.setStringProperty("role", toGroup);

        logger.info("[CourseJMSSender] About to send message: {}", strMessage);
        messageProducer.send(message, completionListener);
    }

}
