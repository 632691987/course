package course.resources;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.NotBlank;
import org.perf4j.aop.Profiled;
import org.springframework.web.bind.annotation.RequestBody;

import course.aop.anotations.IsAdmin;
import course.dto.AdminDTO;
import course.service.AdminService;
import course.system.constant.Setting;

@Path("admins")
@Produces(MediaType.APPLICATION_JSON)
public class AdminResource {
	
	private AdminService adminService;
	
	public AdminResource(AdminService adminService){
		this.adminService = adminService;
	}

	@GET
	@Profiled(logFailuresSeparately = true)
	@IsAdmin
	public Response getAdminList() {
		return Response.ok(adminService.getAdminList()).build();
	}
	
	@GET
	@Profiled(logFailuresSeparately = true)
	@IsAdmin
	@Path("{name}")
	public Response getAdminByName( @NotBlank @PathParam("name") String adminName ) {
		AdminDTO adminDTO = adminService.getAdminByName(adminName);
		return Response.ok(adminDTO).build();
	}
	
	@POST
	@Profiled(logFailuresSeparately = true)
	@Consumes(MediaType.APPLICATION_JSON)
	@IsAdmin
	public Response addAdmin( @NotNull @Valid @RequestBody AdminDTO dto, 
			@HeaderParam(Setting.Resource.HEADER_USER) String username){
		AdminDTO adminDTO = adminService.addAdmin(dto);
		return Response.ok(adminDTO).build();
	}
	
	@DELETE
	@Profiled(logFailuresSeparately = true)
	@Path("{adminName}")
	@IsAdmin
	public Response deleteAdmin(@NotBlank @PathParam("adminName") String adminName){
		adminService.deleteAdmin(adminName);
		return Response.ok().build();
	}
	
	@PUT
	@Profiled(logFailuresSeparately = true)
	@Consumes(MediaType.APPLICATION_JSON)
	@IsAdmin
	public Response updateAdmin( @NotNull @Valid @RequestBody AdminDTO dto, 
			@NotBlank @HeaderParam(Setting.Resource.HEADER_USER) String username){
		AdminDTO adminDTO = adminService.addAdmin(dto);
		return Response.ok(adminDTO).build();
	}
}
