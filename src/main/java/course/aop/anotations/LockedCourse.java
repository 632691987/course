package course.aop.anotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
public @interface LockedCourse {
	
	String ledgerCode() default "ledgerCode";
	String username() default "username";
	boolean orLockedByRequester() default false;

}
