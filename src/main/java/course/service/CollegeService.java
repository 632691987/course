package course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import course.data.model.College;
import course.data.repository.CollegeRepository;

@Service
public class CollegeService {
	
	@Autowired
	private CollegeRepository collegeRepository;
	
	public List<College> getCollegeEntities(){
		return collegeRepository.findAll();
	}
}
