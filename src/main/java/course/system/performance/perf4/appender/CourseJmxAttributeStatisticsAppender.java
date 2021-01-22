package course.system.performance.perf4.appender;

import static com.google.common.collect.Iterables.filter;
import static java.util.Arrays.asList;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import org.perf4j.aop.Profiled;
import org.perf4j.logback.JmxAttributeStatisticsAppender;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;

public class CourseJmxAttributeStatisticsAppender extends JmxAttributeStatisticsAppender {
	
	private static final String FAILURE_SUFFIX = ".failure";

	private static final String SUCCESS_SUFFIX = ".success";

	/**
	 * <p>
	 * Sets the tag names to expose by scanning the provided class name. Only
	 * methods annotated with Profiled and one of GET, POST, PUT, DELETE will be
	 * considered.
	 * </p>
	 *
	 * <p>
	 * If the method is set up to log failures separately, like
	 * this @Profiled(logFailuresSeparately = true), the tag names will be
	 * "methodName.success, methodName.failure". If not, the tag name will be
	 * "methodName"
	 * </p>
	 *
	 * @param tagNamesToExpose
	 *            Class name of the resource that should be exposed.
	 */
	@Override
	public void setTagNamesToExpose(String tagNamesToExpose) {
		String tagNames = createTagNamesForClassName(tagNamesToExpose);
		super.setTagNamesToExpose(tagNames);
	}

	private String createTagNamesForClassName(String className) {
		try {
			Class<?> clazz = Class.forName(className);
			return createTagNamesForClass(clazz);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(String.format("Class '%s' not found", className), e);
		}
	}

	private String createTagNamesForClass(Class<?> clazz) {
		final List<String> tagNames = new ArrayList<>();
		StringJoiner sj = new StringJoiner(",");

		for (Method method : filter(asList(clazz.getMethods()), isJaxrsMethod())) {
			tagNames.addAll(createTagNamesForMethod(method));
		}
		tagNames.stream().forEach(sj::add);
		return sj.toString();
	}

	private Predicate<Method> isJaxrsMethod() {
		return new Predicate<Method>() {
			@Override
			public boolean apply(Method method) {
				return method.isAnnotationPresent(PUT.class) || method.isAnnotationPresent(POST.class)
						|| method.isAnnotationPresent(GET.class) || method.isAnnotationPresent(DELETE.class);
			}
		};
	}

	private List<String> createTagNamesForMethod(Method method) {
		if (method.isAnnotationPresent(Profiled.class)) {
			Profiled profiled = method.getAnnotation(Profiled.class);

			if (profiled.logFailuresSeparately()) {
				return ImmutableList.of(method.getName() + SUCCESS_SUFFIX, method.getName() + FAILURE_SUFFIX);
			} else {
				return ImmutableList.of(method.getName());
			}
		}

		return new ArrayList<>();
	}
}
