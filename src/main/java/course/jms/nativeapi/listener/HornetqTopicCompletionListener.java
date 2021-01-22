package course.jms.nativeapi.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.CompletionListener;
import javax.jms.Message;

/**
 * Created by yan on 2016/3/19.
 */
@Component(value = "topicCompletionListener")
public class HornetqTopicCompletionListener implements CompletionListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onCompletion(Message message) {
        logger.info("HornetqTopicCompletionListener onComplete");
    }

    @Override
    public void onException(Message message, Exception exception) {
        logger.info("HornetqTopicCompletionListener onException");
    }
}
