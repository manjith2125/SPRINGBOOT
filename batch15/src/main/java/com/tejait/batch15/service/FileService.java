package com.tejait.batch15.service;
 
import java.io.IOException;
 
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
 
import com.tejait.batch15.model.File;
 
@Service

public interface FileService {
 
	String uploadFile(MultipartFile file) throws IOException;
 
	File getFile(Long id);
 
}
 