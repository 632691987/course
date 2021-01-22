package course.resources;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import course.service.JMSService;

@Path("jms")
@Produces(MediaType.APPLICATION_JSON)
public class JmsResource {
	
	private JMSService jmsService;
	
	@Autowired
	public JmsResource(JMSService jmsService){
		this.jmsService = jmsService;
	}
	
	@PUT
	public Response sendMessageToQueueA(@NotNull @FormParam("messagecontent") String messagecontent){
		jmsService.sendToQueueA(messagecontent);
		return Response.accepted().build();
	}
	
	@PUT
	@Path("queueB")
	public Response sendMessageToQueueB(@NotNull @FormParam("messagecontent") String messagecontent){
		jmsService.sendToQueueB(messagecontent);
		return Response.accepted().build();
	}

	@PUT
	@Path("queueE")
	public Response sendMessageToQueueE(@NotNull @FormParam("messagecontent") String messagecontent) {
		jmsService.sendToQueueE(messagecontent);
		return Response.accepted().build();
	}

}
