package course.jms.nativeapi.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.CompletionListener;
import javax.jms.Message;

/**
 * Created by yan on 2016/3/19.
 */
@Component(value = "queueCompletionListener")
public class HornetqQueueCompletionListener implements CompletionListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onCompletion(Message message) {
        logger.info("HornetqQueueCompletionListener event = {}, content = Send message complete", "onCompletion");
    }

    @Override
    public void onException(Message message, Exception exception) {
        logger.info("HornetqQueueCompletionListener event = {}, content = {}", "onException", exception.getMessage());
    }
}
