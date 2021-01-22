package course.aop.anotations;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ADMIN')")
public @interface IsAdmin {

}
