package course.config.jms.hornetq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Topic;

import course.jms.nativeapi.largemessage.LargeMessageListener;
import course.jms.nativeapi.listener.HornetqQueueListener;

@Configuration
public class HornetqConfig {

    @Autowired
    private ConnectionFactory connectionFactory;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public DefaultMessageListenerContainer largeMessageListener(@Value("${fileDownLoadPath}") String fileLocation, @Qualifier("QueueD") Queue QueueD) {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(this.connectionFactory);
        container.setDestination(QueueD);
        container.setMessageListener(new LargeMessageListener(fileLocation));
        return container;
    }

    @Bean
    public DefaultMessageListenerContainer adminMessageListener(@Qualifier("QueueC") Queue queueC) {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(this.connectionFactory);
        container.setDestination(queueC);
        container.setMessageListener(new HornetqQueueListener("admin"));
        container.setMessageSelector("role='admin'");
        return container;
    }

    @Bean
    public DefaultMessageListenerContainer teacherMessageListener(@Qualifier("QueueC") Queue queueC) {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(this.connectionFactory);
        container.setDestination(queueC);
        container.setMessageListener(new HornetqQueueListener("teacher"));
        container.setMessageSelector("role='teacher'");
        return container;
    }

    @Bean
    public DefaultMessageListenerContainer topicAMessageListener(@Qualifier("TopicA") Topic topicA, @Qualifier("topicListener") MessageListener messageListener) {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(this.connectionFactory);
        container.setDestination(topicA);
        container.setMessageListener(messageListener);
        return container;
    }
}
