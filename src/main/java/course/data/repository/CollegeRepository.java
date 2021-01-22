package course.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import course.data.model.College;

public interface CollegeRepository extends JpaRepository<College, Integer>{

}
