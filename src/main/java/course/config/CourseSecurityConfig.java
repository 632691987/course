package course.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import course.system.codec.SystemPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true,securedEnabled=true)
public class CourseSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private Environment enviornment;
	
	private final String SECURITY_VAR_NAME = "security.user.config";
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new InMemoryUserDetailsManager(systemSecuritySetting())).passwordEncoder(new SystemPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.csrf().disable();
	}
	
	private Properties systemSecuritySetting() throws IOException{
		Resource resource = systemSecurityResource(SECURITY_VAR_NAME);
		Properties properties = new Properties();
		properties.load(resource.getInputStream());
		return properties;
	}
	
	private Resource systemSecurityResource(String seucrityUserPath){
		return new ClassPathResource(enviornment.getProperty(seucrityUserPath));
	}
}
