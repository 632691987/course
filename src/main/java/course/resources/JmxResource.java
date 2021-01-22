package course.resources;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import course.service.JmxService;

@Path("jmx")
@Produces(MediaType.APPLICATION_JSON)
public class JmxResource {
	
	private JmxService jmxService;
	
	public JmxResource(JmxService jmxService){
		this.jmxService = jmxService;
	}
	
	@GET
	public Response getValue(){
		String value = jmxService.getValue();
		JsonObject jsonObject = Json.createObjectBuilder().add("value", value).build();
		return Response.ok(jsonObject).build();
	}

}
