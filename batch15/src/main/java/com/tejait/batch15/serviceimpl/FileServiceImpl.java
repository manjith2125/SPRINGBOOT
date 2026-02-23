package com.tejait.batch15.serviceimpl;
 
import java.io.IOException;
 
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
 
import com.tejait.batch15.model.File;

import com.tejait.batch15.repository.FileRepository;

import com.tejait.batch15.service.FileService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service

public class FileServiceImpl implements FileService{
 
	

	    FileRepository repository;
 
	 @Override

	 public String uploadFile(MultipartFile file) throws IOException {

		 File fileEntity = new File();
 
	        fileEntity.setFileName(file.getOriginalFilename());

	        fileEntity.setFileType(file.getContentType());

	        fileEntity.setData(file.getBytes());
 
	        repository.save(fileEntity);
 
	        return "File uploaded successfully into DB";

	    }
 
	 @Override

	 public File getFile(Long id) {

		 return repository.findById(id)
				 .orElseThrow(() -> new RuntimeException("File not found with id: " + id));

	 }

}
 