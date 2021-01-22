package course.service;

import java.util.Arrays;

import javax.management.MBeanServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

@Service
@ManagedResource
public class JmxService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AnnotationMBeanExporter annotationMBeanExporter;
	
	private String value = "default";
	
	@ManagedOperation
	public String getValue() {
		checkMBeanInfo();
		return value;
	}
	
	@ManagedOperation
	public void setValue(String value) {
		this.value = value;
	}
	
	public void checkMBeanInfo(){
		MBeanServer MBeanServer = annotationMBeanExporter.getServer();
		Arrays.asList(MBeanServer.getDomains()).stream().forEach(logger::info);
	}
}
