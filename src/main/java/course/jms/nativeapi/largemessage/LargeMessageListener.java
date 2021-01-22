package course.jms.nativeapi.largemessage;

import org.apache.commons.io.IOUtils;
import org.hornetq.api.jms.HornetQJMSConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.StringJoiner;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;


public class LargeMessageListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String fileFolder;

    public LargeMessageListener(String fileFolder) {
        this.fileFolder = fileFolder;
    }

    @Override
    public void onMessage(Message message) {
        logger.info("LargeMessageListener about receive message....");

        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutput = null;
        try {
            BytesMessage messageReceived = (BytesMessage) message;

            String fileName = messageReceived.getStringProperty("fileName");
            String toClient = messageReceived.getStringProperty("toClient");
            String fileLocation = jmsMessageLocation(fileFolder, toClient, fileName);
            File outputFile = new File(fileLocation);
            createFolder(outputFile);

            fileOutputStream = new FileOutputStream(outputFile);
            bufferedOutput = new BufferedOutputStream(fileOutputStream);
            messageReceived.setObjectProperty(HornetQJMSConstants.JMS_HORNETQ_SAVE_STREAM, bufferedOutput);
        } catch (JMSException e) {
            logger.error("Error occurs when LargeMessageListener receiving message: type = {}, content = {}", e.getErrorCode(), e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error("Error occurs when LargeMessageListener receiving message: content = {}", e.getMessage());
        } finally {
            IOUtils.closeQuietly(bufferedOutput);
            IOUtils.closeQuietly(fileOutputStream);
        }

        logger.info("LargeMessageListener complete receive message....");
    }

    private String jmsMessageLocation(String fileLocation, String toClient, String fileName) {
        StringJoiner stringJoiner = new StringJoiner(File.separator);
        stringJoiner.add(fileLocation).add(toClient).add(fileName);
        return stringJoiner.toString();
    }

    private void createFolder(File downloadFile) {
        String absolutePath = downloadFile.getAbsolutePath();
        String folderPath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
        new File(folderPath).mkdir();
    }
}
