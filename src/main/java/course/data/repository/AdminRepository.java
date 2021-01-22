package course.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import course.data.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	
	public Optional<Admin> findById(int id);
	
	public Optional<Admin> findOneByNameAndPassword(String name, String password);
	
	public Optional<Admin> findByName(String name);
	
}