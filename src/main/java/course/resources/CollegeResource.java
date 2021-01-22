package course.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import course.service.CollegeService;

@Path("college")
@Produces(MediaType.APPLICATION_JSON)
public class CollegeResource {
	
	private CollegeService collegeService;
	
	public CollegeResource(CollegeService collegeService){
		this.collegeService = collegeService;
	}
	
	@GET
	@Path("all")
	public Response getList(){
		return Response.ok(collegeService.getCollegeEntities()).build();
	}
	
}
