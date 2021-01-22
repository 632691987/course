package course.system.exception;

@SuppressWarnings("serial")
public class CourseGenericException extends RuntimeException{
	
	public CourseGenericException(){
		super();
	}
	
	public CourseGenericException(String message){
		super(message);
	}
	
	public CourseGenericException(Throwable cause){
		super(cause);
	}
	
	public CourseGenericException(String message, Throwable cause) {
		super(message, cause);
	}
}
