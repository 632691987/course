package course.jms.nativeapi.largemessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.jms.BytesMessage;
import javax.jms.CompletionListener;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

@Component
public class LargeMessageSender {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private ConnectionFactory connectionFactory = null;

    private Connection connection = null;

    private Session session = null;

    private MessageProducer messageProducer = null;

    private CompletionListener completionListener = null;

    @Autowired
    public LargeMessageSender(
            ConnectionFactory connectionFactory,
            @Qualifier("queueCompletionListener") CompletionListener completionListener,
            @Qualifier("QueueD") Queue queueD
    ) throws JMSException {
        this.connectionFactory = connectionFactory;
        this.completionListener = completionListener;
        renconnect(queueD);
    }

    private void renconnect(Queue initQ) throws JMSException {
        connection = connectionFactory.createConnection();
        session = connection.createSession();
        messageProducer = session.createProducer(initQ);
    }

    public void sentMessage(String fileInput, String toGroup) throws JMSException, FileNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(fileInput);
        BufferedInputStream bufferedInput = new BufferedInputStream(fileInputStream);
        BytesMessage message = session.createBytesMessage();

        message.setObjectProperty("JMS_HQ_InputStream", bufferedInput);
        message.setStringProperty("fileName", fileInput.substring(fileInput.lastIndexOf(File.separator) + 1));
        message.setStringProperty("toClient", toGroup);
//        message.setJMSReplyTo(null);
//        message.setJMSCorrelationID(null);

        logger.info("About to send file: {}", fileInput);
        messageProducer.send(message, completionListener);
    }
}
