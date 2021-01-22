package course.exceptions.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import course.exceptions.BasicExceptionMapper;
import course.exceptions.EntityExistException;
import course.exceptions.EntityNotFoundException;

public class CourseExceptionMapper {
	
	@Provider
	public static class EntityNotFoundExceptionMapper extends BasicExceptionMapper implements ExceptionMapper<EntityNotFoundException>{
		@Override
		public Response toResponse(EntityNotFoundException exception) {
			return this.buildResponse(Status.NOT_FOUND, exception);
		}
	}
	
	@Provider
	public static class EntityExistExceptionMapper extends BasicExceptionMapper implements ExceptionMapper<EntityExistException>{
		@Override
		public Response toResponse(EntityExistException exception) {
			return this.buildResponse(Status.BAD_REQUEST, exception.getMessage());
		}
	}
}
