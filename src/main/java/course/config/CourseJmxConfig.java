package course.config;

import org.perf4j.slf4j.aop.TimingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;

@Configuration
@EnableMBeanExport
public class CourseJmxConfig {
	/**
	 * 注意
	 * 1, For WINDOWS: cacls jmx-user.config /P Administradores:R
	 * 2, For Linux  : chmod 600 jmx-user.config
	 * 3, 在 JVM 处加上 -Dcom.sun.management.config.file=C:\workspace\project\Course01\springboot-jersey\src\main\resources\jmx.properties
	 * 4, 连接 service:jmx:rmi:///jndi/rmi://127.0.0.1:9513/jmxrmi
	 */
	
	
	/**
	 * For pefr4j MBean
	 */
	@Bean
	public TimingAspect timingAspect() {
        return new TimingAspect();
    }
}
