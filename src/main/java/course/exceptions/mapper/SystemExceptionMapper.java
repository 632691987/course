package course.exceptions.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.security.authentication.BadCredentialsException;

import course.exceptions.BasicExceptionMapper;

public class SystemExceptionMapper {
	
	
	@Provider
	public static class BadCredentialsExceptionMapper extends BasicExceptionMapper implements ExceptionMapper<BadCredentialsException>{
		@Override
		public Response toResponse(BadCredentialsException exception) {
			return this.buildResponse(Status.UNAUTHORIZED, exception);
		}
	}
	
	
}
