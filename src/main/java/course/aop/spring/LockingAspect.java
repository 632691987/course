package course.aop.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import course.aop.anotations.LockedCourse;
import course.service.CourseService;

@Aspect
@Component
public class LockingAspect extends AbstractAspect {
	
	private CourseService courseService;

	@Autowired
	public LockingAspect(CourseService courseService) {
		this.courseService = courseService;
	}

	@Pointcut("execution(* *(..))")
	public void anyMethod() {
	}

	@Before("anyMethod() && @annotation(lockedCourse)")
	public void checkCourseIsLocked(JoinPoint jp, LockedCourse lockedCourse) {
		String ledgerCode = getParameterValue(jp.getSignature(), jp.getArgs(), lockedCourse.ledgerCode());
		String username = getParameterValue(jp.getSignature(), jp.getArgs(), lockedCourse.username());
	}
}