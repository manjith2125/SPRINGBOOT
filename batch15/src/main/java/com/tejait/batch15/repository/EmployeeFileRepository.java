package com.tejait.batch15.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejait.batch15.model.EmployeeFile;

public interface EmployeeFileRepository extends JpaRepository<EmployeeFile, Long> {

	 Optional<EmployeeFile> findFirstByFileType(String fileType);
	
}
