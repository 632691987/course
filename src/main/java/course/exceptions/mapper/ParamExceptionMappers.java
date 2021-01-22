package course.exceptions.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.glassfish.jersey.server.ParamException;

import course.exceptions.BasicExceptionMapper;

public class ParamExceptionMappers {
	@Provider
	public static class ParamExceptionMapper extends BasicExceptionMapper implements ExceptionMapper<ParamException> {
		@Override
		public Response toResponse(ParamException exception) {
			String message = buildMessageFromCauseAndParameter(ExceptionUtils.getRootCause(exception),
					exception.getParameterName());
			return this.buildResponse(Status.BAD_REQUEST, message);
		}

		private String buildMessageFromCauseAndParameter(Throwable cause, String parameterName) {
			String message;
			if (cause instanceof NumberFormatException) {
				message = parameterName + " must be a valid number";
			} else if (cause instanceof IllegalArgumentException) {
				message = cause.getMessage();
			} else {
				message = "Invalid value for " + parameterName;
			}
			return message;
		}
	}
}