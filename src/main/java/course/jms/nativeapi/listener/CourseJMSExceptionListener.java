package course.jms.nativeapi.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

/**
 * Created by yan on 2016/3/26.
 */
public class CourseJMSExceptionListener implements ExceptionListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onException(JMSException exception) {
        logger.error("CourseJMSExceptionListener JMS error detected, content = ", exception.getMessage());
    }
}
