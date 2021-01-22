package course.exceptions.mapper;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path.Node;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import course.exceptions.BasicExceptionMapper;

public class ValidationExceptionMappers {
	
	private static final String RESOURCE_PACKAGE = "course.resources";
	
	@Provider
	public static class ConstraintViolationExceptionMapper extends BasicExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
		@Override
		public Response toResponse(ConstraintViolationException exception) {
			boolean isService = isServiceException(exception);
			String message = buildConstraintViolationExceptionMessage(exception, isService);
			if (isService) {
				return this.buildResponse(Status.INTERNAL_SERVER_ERROR, message);
			} else {
				return this.buildResponse(Status.BAD_REQUEST, message);
			}
		}

		private String buildConstraintViolationExceptionMessage(ConstraintViolationException exception, boolean isService) {
			final StringBuilder msg = new StringBuilder();
			for (final ConstraintViolation<?> violation : exception.getConstraintViolations()) {
				msg.append(msg.length() > 0 ? ", " : "");
				if (!isService) {
					String arg = null;
					for (Node node : violation.getPropertyPath()) {
						arg = node.getName();
					}
					if (arg != null) {
						msg.append(arg);
					}
				}
				msg.append(" ").append(violation.getMessage());
			}
			return msg.toString();
		}

		private boolean isServiceException(ConstraintViolationException exception) {
			boolean isService = true;
			for (final ConstraintViolation<?> violation : exception.getConstraintViolations()) {
				if (violation.getRootBeanClass().getPackage().getName().equals(RESOURCE_PACKAGE)) {
					isService = false;
				}
			}
			return isService;
		}
	}

	@Provider
	public static class ValidationExceptionMapper extends BasicExceptionMapper implements ExceptionMapper<ValidationException> {
		@Override
		public Response toResponse(ValidationException exception) {
			String message = buildValidationExceptionMessage(exception);
			return this.buildResponse(Status.BAD_REQUEST, message);
		}

		private String buildValidationExceptionMessage(ValidationException exception) {
			return "A parameter is missing or is invalid: " + exception.getMessage();
		}
	}
}

