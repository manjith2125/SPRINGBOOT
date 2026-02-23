package com.tejait.batch15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tejait.batch15.model.EmployeeFile;
import com.tejait.batch15.serviceimpl.EmployeeFileServiceImpl;

@RestController
@RequestMapping("/files")
public class EmployeeFileController {

    @Autowired
    private EmployeeFileServiceImpl service;

    // Generate All Files
    @PostMapping("/generate")
    public ResponseEntity<String> generateFiles() throws Exception {
        return ResponseEntity.ok(service.generateAndStoreAll());
    }

    // Download by Type
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(
            @RequestParam String type) {

        EmployeeFile file = service.getFileByType(type);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + file.getFileName())
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .body(file.getFileData());
    }
}

