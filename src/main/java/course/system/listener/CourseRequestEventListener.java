package course.system.listener;

import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * https://jersey.java.net/documentation/latest/monitoring_tracing.html
 * @author yan
 *
 */
public class CourseRequestEventListener implements RequestEventListener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    private final int requestNumber;
    private final long startTime;
 
    public CourseRequestEventListener(int requestNumber) {
        this.requestNumber = requestNumber;
        startTime = System.currentTimeMillis();
    }
 
    @Override
    public void onEvent(RequestEvent event) {
        switch (event.getType()) {
        case START: 
        case MATCHING_START: 
        case LOCATOR_MATCHED: 
        case SUBRESOURCE_LOCATED: 
        case REQUEST_MATCHED: 
        case REQUEST_FILTERED: 
        case RESOURCE_METHOD_FINISHED: 
        case RESP_FILTERS_START: 
        case RESP_FILTERS_FINISHED: 
        case ON_EXCEPTION: 
        case EXCEPTION_MAPPER_FOUND: 
        case EXCEPTION_MAPPING_FINISHED: 
        	logger.debug(String.format("RequestEvent [%s] triggered", event.getType()));
        	break;
        case RESOURCE_METHOD_START: 
        	logger.debug(String.format("Resource method [%s] started for request [%s]", event.getUriInfo().getMatchedResourceMethod().getHttpMethod(), requestNumber));
        	break;
        case FINISHED: 
        	logger.debug(String.format("Request [%s] finished. Processing time [%d] ms.", requestNumber, System.currentTimeMillis() - startTime));
        	break;
        }
    }
}