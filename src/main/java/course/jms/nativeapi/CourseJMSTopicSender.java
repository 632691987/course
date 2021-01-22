package course.jms.nativeapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.jms.CompletionListener;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 * Created by yan on 2016/3/19.
 */
@Component
public class CourseJMSTopicSender {

    private ConnectionFactory connectionFactory = null;

    private Connection connection = null;

    private Session session = null;

    private MessageProducer messageProducer = null;

    private CompletionListener completionListener;

    @Autowired
    private CourseJMSTopicSender(
            ConnectionFactory connectionFactory,
            @Qualifier("topicCompletionListener") CompletionListener completionListener,
            @Qualifier("TopicA") Topic topicA
    ) throws JMSException {
        this.connectionFactory = connectionFactory;
        this.completionListener = completionListener;
        renconnect(topicA);
    }

    private void renconnect(Topic destination) throws JMSException {
        connection = connectionFactory.createConnection();
        session = connection.createSession();
        messageProducer = session.createProducer(destination);
    }

    public void sentMessage(String strMessage) throws JMSException {
        TextMessage message = session.createTextMessage(strMessage);
        messageProducer.send(message, completionListener);
    }
}
