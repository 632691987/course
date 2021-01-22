package course.resources;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import course.dto.PingDto;

//DTO testing
@Path("dto")
@Produces(MediaType.APPLICATION_JSON)
public class DTOResource {
	
	@GET
	@Path("jaxb")
	public Response testJAXB(){
		PingDto dto = new PingDto();
		dto.setKey("key");
		dto.setValue("value");
		
		return Response.ok(dto).build();
	}
	
	@GET
	@Path("jsonobject")
	public Response testJsonObject(){
		JsonObject jsonObject = Json.createObjectBuilder().add("name", "fuck").add("cao", "fuck2").build();
		return Response.ok(jsonObject).build();
	}
	
}
