package course.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import course.data.repository.CourseRepository;

@Service
public class CourseService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CourseRepository repository;
	
}
