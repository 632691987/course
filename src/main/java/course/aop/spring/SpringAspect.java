package course.aop.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SpringAspect {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut(" execution(* course.service.*.*(..))")
	private void anyPublicOperation() {}
	
	@Before("anyPublicOperation()")
    public void doAccessCheck(JoinPoint jp) {
		logger.debug(jp.getSignature().getName());//function name
		logger.debug(jp.getThis().getClass().getName());//target class name
    }
	
}
