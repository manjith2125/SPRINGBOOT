package com.tejait.batch15.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.tejait.batch15.model.File;

public interface FileRepository extends JpaRepository<File, Long>{

}
