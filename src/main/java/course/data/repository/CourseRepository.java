package course.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import course.data.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
