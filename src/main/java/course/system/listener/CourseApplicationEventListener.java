package course.system.listener;

import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseApplicationEventListener implements ApplicationEventListener{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private volatile int requestCnt = 0;

	@Override
	public void onEvent(ApplicationEvent event) {
		switch(event.getType()){
			case INITIALIZATION_START: 
			case INITIALIZATION_APP_FINISHED: 
			case INITIALIZATION_FINISHED: 
			case DESTROY_FINISHED: 
			case RELOAD_FINISHED: 
				logger.debug(String.format("ApplicationEvent [%s] triggered", event.getType()));
				break;
		}
	}

	@Override
	public RequestEventListener onRequest(RequestEvent requestEvent) {
		requestCnt++;
        return new CourseRequestEventListener(requestCnt);
	}

}
