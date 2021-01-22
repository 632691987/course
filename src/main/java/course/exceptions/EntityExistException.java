package course.exceptions;

import course.system.exception.CourseGenericException;

@SuppressWarnings("serial")
public class EntityExistException extends CourseGenericException {

	public static final String EXCEPTION_MESSAGE = "%s already exist, name = {%s}";

	public EntityExistException(EntityType entityType, String name) {
		super(String.format(EXCEPTION_MESSAGE, entityType, name));
	}

}
