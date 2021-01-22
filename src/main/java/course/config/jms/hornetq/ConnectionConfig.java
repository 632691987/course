package course.config.jms.hornetq;

import com.google.common.base.Strings;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.api.jms.JMSFactoryType;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

import course.config.properties.CourseJMSConfiguration;

@Configuration
public class ConnectionConfig {

    @Autowired
    private CourseJMSConfiguration jmsConfig;

    @Bean
    //@ConditionalOnProperty(name = "spring.hornetq.embedded.enabled", havingValue = "false")
    public ConnectionFactory credentialConnectionFactory() {
        UserCredentialsConnectionFactoryAdapter adapter = new UserCredentialsConnectionFactoryAdapter();
        adapter.setUsername(jmsConfig.getUsername());
        adapter.setPassword(jmsConfig.getPassword());
        adapter.setTargetConnectionFactory(connectionFactory());
        return adapter;
    }

    private ConnectionFactory connectionFactory() {
        ConnectionFactory factory = (ConnectionFactory) HornetQJMSClient.createConnectionFactoryWithHA(JMSFactoryType.QUEUE_XA_CF, transportConfiguration());
        return factory;
    }

    private TransportConfiguration[] transportConfiguration() {
        List<Map<String, Object>> connectionInfos = connectionInfos();
        return connectionInfos.stream()
                .map(info -> new TransportConfiguration(NettyConnectorFactory.class.getName(), info))
                .collect(Collectors.toList()).toArray(new TransportConfiguration[0]);
    }

    private List<Map<String, Object>> connectionInfos() {

        String cluster = jmsConfig.getCluster();
        List<Map<String, Object>> connectionInfos = new ArrayList<>();

        if (Strings.isNullOrEmpty(cluster)) {
            Map<String, Object> params = new HashMap<String, Object>();
            String host = jmsConfig.getHost();
            String port = jmsConfig.getPort();
            params.put("host", host);
            params.put("port", port);
            connectionInfos.add(params);
        } else {
            String[] hosts = cluster.split(";");
            connectionInfos = Arrays.asList(hosts).stream().map(_host -> _host.split(":")).map(arr -> {
                Map<String, Object> params = new HashMap<>();
                params.put("host", arr[0]);
                params.put("port", arr[1]);
                return params;
            }).collect(Collectors.toList());
        }

        return connectionInfos;
    }

    @Bean(name = "QueueA")
    public Queue QueueA() {
        return HornetQJMSClient.createQueue(jmsConfig.getQueueA());
    }

    @Bean(name = "QueueB")
    public Queue QueueB() {
        return HornetQJMSClient.createQueue(jmsConfig.getQueueB());
    }

    @Bean(name = "QueueC")
    public Queue QueueC() {
        return HornetQJMSClient.createQueue(jmsConfig.getQueueC());
    }

    @Bean(name = "QueueD")
    public Queue QueueD() {
        return HornetQJMSClient.createQueue(jmsConfig.getQueueD());
    }

    @Bean(name = "QueueE")
    public Queue QueueE() {
        return HornetQJMSClient.createQueue(jmsConfig.getQueueE());
    }

    @Bean(name = "QueueF")
    public Queue QueueF() {
        return HornetQJMSClient.createQueue(jmsConfig.getQueueF());
    }

    @Bean(name = "TopicA")
    public Topic TopicA() {
        return HornetQJMSClient.createTopic(jmsConfig.getTopicA());
    }

}
