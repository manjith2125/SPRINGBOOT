package com.tejait.batch15.controller;
 
import java.io.IOException;
 
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.multipart.MultipartFile;
 
import com.tejait.batch15.model.File;

import com.tejait.batch15.service.FileService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController

@RequestMapping("/file")

public class FileController {
 
	 

	    FileService service;

	 @PostMapping("/upload")

	    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
 
	        String message = service.uploadFile(file);
 
	        return ResponseEntity.status(HttpStatus.CREATED).body(message);

	    }


	 @GetMapping("/download/{id}")

	    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
 
	        File file = service.getFile(id);
 
	        return ResponseEntity.ok()

	                .contentType(MediaType.parseMediaType(file.getFileType()))

	                .header(HttpHeaders.CONTENT_DISPOSITION,

	                        "attachment; filename=\"" + file.getFileName() + "\"")

	                .body(file.getData());

	    }

}
 