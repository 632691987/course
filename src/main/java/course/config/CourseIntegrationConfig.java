package course.config;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.jms.Jms;
import org.springframework.integration.dsl.jms.JmsMessageDrivenChannelAdapterSpec;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.jms.listener.DefaultMessageListenerContainer;	

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class CourseIntegrationConfig {
	
	public static final String PKG_ENDPOINT = "course.integration.endpoint";
	public static final String PKG_MSG_GW   = "course.integration.gateway";

	@Autowired
	@Qualifier("QueueF")
	public Queue queueF;

    public IntegrationFlow courseIntegrationSetUp() {
        return IntegrationFlows.from("").get();
    }

    private interface TestStatementsUploadFlow{
		public void uploadFile(String payload);
    }

	private JsonToObjectTransformer jsonToObject() {
		return new JsonToObjectTransformer();
	}

	private JmsMessageDrivenChannelAdapterSpec<?> jmsUploadQueue(ConnectionFactory connectionFactory) {
		return Jms.messageDrivenChannelAdapter(messageListenerContainer(connectionFactory))
				.autoStartup(false);
				//.errorChannel(errorChannel);
	}

	private DefaultMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
		container.setSessionTransacted(true);
		container.setConcurrentConsumers(10);
		container.setAcceptMessagesWhileStopping(false);
		container.setConnectionFactory(connectionFactory);
		container.setDestination(queueF);
		return container;
	}

}
