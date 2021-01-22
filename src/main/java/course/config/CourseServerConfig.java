package course.config;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@PropertySource("classpath:SystemConfig.properties")
public class CourseServerConfig {
	
	private static final String SERVLET_NAME = "course";
	private static final String SERVLET_CONTEXT = "/course/*";
	
	@Autowired
	private ResourceConfig resourceConfig;
	
	@Bean
	public ServletRegistrationBean jerseyServlet(){
		ServletRegistrationBean jerseyServlet = new ServletRegistrationBean(new ServletContainer(resourceConfig),SERVLET_CONTEXT);
		jerseyServlet.setName(SERVLET_NAME);
		jerseyServlet.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return jerseyServlet;
	}
	
	/**
	 * Here should use <artifactId>jetty-server</artifactId>
	 */
	@Bean
	public JettyEmbeddedServletContainerFactory jettyServletContainer(
			@Value("${Jetty.Thread.max}") Integer jettyThreadMax,
			@Value("${Jetty.Thread.min}") Integer jettyThreadMin,
			@Value("${server.session-timeout}") Integer sessionTimeout,
			@Value("${server.port}") Integer port){
		final JettyEmbeddedServletContainerFactory servletContainerFactory = new JettyEmbeddedServletContainerFactory(port);
		servletContainerFactory.addServerCustomizers(
				server -> {
					final QueuedThreadPool threadPool = (QueuedThreadPool)server.getThreadPool();
					threadPool.setMaxThreads(jettyThreadMax);
					threadPool.setMinThreads(jettyThreadMin);
//		            ServerConnector serverConnector =
//		                    new ServerConnector(server, firstNonNull(jettyProperties.getAcceptors(), DEFAULT_ACCEPTORS), firstNonNull(
//		                            jettyProperties.getSelectors(), DEFAULT_SELECTORS));
//
//		            serverConnector.setPort(firstNonNull(serverProperties.getPort(), DEFAULT_PORT));
//		            serverConnector.setIdleTimeout(firstNonNull(jettyProperties.getMaxIdleTime(), DEFAULT_MAX_IDLE_TIME));
//		            serverConnector.setAcceptQueueSize(firstNonNull(jettyProperties.getAcceptQueueSize(), DEFAULT_ACCEPT_QUEUE_SIZE));
//		            serverConnector.setAcceptorPriorityDelta(firstNonNull(jettyProperties.getAcceptorPriorityDelta(),DEFAULT_ACCEPTOR_PRIORITY_DELTA));
//
//		            server.setConnectors(new Connector[] { serverConnector });
				});
		servletContainerFactory.setSessionTimeout(sessionTimeout, TimeUnit.MINUTES);
		servletContainerFactory.setRegisterDefaultServlet(false);
		return servletContainerFactory;
	}
	
	@Bean
	public ObjectMapper objectMapper(){
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
		return objectMapper;
	}
	
	
	
}
