package course.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.http.HttpStatus;

import course.system.exception.ErrorDTO;

public class BasicExceptionMapper {
	
	public Response buildResponse(Status status, Throwable e){
		return buildResponse(status, e.getMessage());
	}
	
	public Response buildResponse(Status status, String errorMessage){
		return buildResponse(status.getStatusCode(), errorMessage);
	}
	
	public Response buildResponse(HttpStatus status, String errorMessage){
		return buildResponse(status.value(), errorMessage);
	}
	
	private Response buildResponse(int status, String errorMessage){
		ErrorDTO dto = buildErrorDTO(status, errorMessage);
		return Response.status(status).entity(dto).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
	
	private ErrorDTO buildErrorDTO(int status, String errorMessage){
		ErrorDTO dto = new ErrorDTO();
		dto.setHttpCode(status);
		dto.setMessage(errorMessage);
		return dto;
	}
	
}
