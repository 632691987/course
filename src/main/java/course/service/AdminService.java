package course.service;

import static course.exceptions.EntityNotFoundException.newEntityNotFoundException;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import course.data.model.Admin;
import course.data.repository.AdminRepository;
import course.dozer.DozerMapper;
import course.dto.AdminDTO;
import course.exceptions.AdminExistException;
import course.exceptions.EntityType;

@Service
public class AdminService {

	private AdminRepository adminRepository;

	private DozerMapper mapper;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public AdminService(AdminRepository adminRepository, DozerMapper mapper) {
		this.adminRepository = adminRepository;
		this.mapper = mapper;
	}

	@Transactional(readOnly = true)
	public List<AdminDTO> getAdminList() {
		List<Admin> adminEntities = adminRepository.findAll();
		return mapper.map(adminEntities, AdminDTO.class);
	}

	@Transactional(readOnly = true)
	public AdminDTO getAdminByName(String adminName) {
		Admin admin = adminRepository.findByName(adminName).orElseThrow(newEntityNotFoundException(EntityType.Admin, adminName));
		return mapper.map(admin, AdminDTO.class);
	}

	@Transactional
	public AdminDTO addAdmin(AdminDTO dto) {
		verifyIfAdminExistOrThrow(dto);
		Admin admin = adminRepository.save(mapper.map(dto, Admin.class));
		return mapper.map(admin, AdminDTO.class);
	}

	@Transactional
	public boolean deleteAdmin(String adminName) {
		Admin admin = adminRepository.findByName(adminName)
				.orElseThrow(newEntityNotFoundException(EntityType.Admin, adminName));
		adminRepository.delete(admin);
		return true;
	}
	
	@Transactional
	public AdminDTO updateAdmin(AdminDTO dto, String adminName) {
		verifyIfAdminNotExistOrThrow(adminName);
		verifyIfAdminNotExistOrThrow(dto.getName());
		Admin admin = mapper.map(dto, Admin.class);
		
		return null;
	}

	private void verifyIfAdminExistOrThrow(AdminDTO dto) {
		verifyIfAdminExistOrThrow(dto.getName());
	}

	private void verifyIfAdminExistOrThrow(String adminName) {
		adminRepository.findByName(adminName).ifPresent(admin -> {
			logger.error("Admin already exist, name = {}", adminName);
			throw new AdminExistException(adminName);
		});
	}
	
	private void verifyIfAdminNotExistOrThrow(String adminName) {
		adminRepository.findByName(adminName).orElseThrow(newEntityNotFoundException(EntityType.Admin, adminName));
	}

}