package course.exceptions;

import java.util.function.Supplier;

import course.system.exception.CourseGenericException;

@SuppressWarnings("serial")
public class EntityNotFoundException extends CourseGenericException{
	
	private static final String EXCEPTION_MESSAGE = "%s not exist, name = {%s}";
	
	public EntityNotFoundException(String errorMessage){
		super(errorMessage);
	}
	
	public EntityNotFoundException(EntityType entityType, String name){
		super(String.format(EXCEPTION_MESSAGE, entityType, name));
	}
	
	public static Supplier<EntityNotFoundException> newEntityNotFoundException(EntityType entityType, String name){
		return () -> new EntityNotFoundException(entityType, name);
	}
	
}
