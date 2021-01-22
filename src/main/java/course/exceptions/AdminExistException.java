package course.exceptions;

import java.util.function.Supplier;

@SuppressWarnings("serial")
public class AdminExistException extends EntityExistException{
	
	public AdminExistException(String name) {
		super(EntityType.Admin, name);
	}
	
	public static Supplier<AdminExistException> newAdminExistException(String name){
		return () -> new AdminExistException(name);
	}
	
}
