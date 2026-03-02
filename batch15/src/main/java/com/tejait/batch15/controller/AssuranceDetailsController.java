package com.tejait.batch15.controller;

import com.tejait.batch15.model.AssuranceDetails;
import com.tejait.batch15.service.AssuranceDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/loans")
public class AssuranceDetailsController {

    private final AssuranceDetailsService service;

    // SUBMIT BUTTON (UPLOAD)
    @PostMapping("/readJson")
    public ResponseEntity<List<AssuranceDetails>> uploadJson(
            @RequestParam("file") MultipartFile file,
            @RequestHeader(value = "Referer", defaultValue = "") String referer) {

        // Extract appId from Referer URL like "http://localhost:4200/assurancedetails?id=2"
        int appId = 1;
        if (referer.contains("id=")) {
            try {
                appId = Integer.parseInt(referer.split("id=")[1].split("&")[0]);
            } catch (Exception e) {
                appId = 1;
            }
        }

        service.saveFromJson(appId, file);
        return ResponseEntity.ok(service.getAllByAppId(appId));
    }

    // SAVE CHANGES BUTTON
    @PostMapping("/saveJsonfileData/{appId}")
    public ResponseEntity<List<AssuranceDetails>> saveChanges(
            @PathVariable int appId,
            @RequestBody List<AssuranceDetails> list) {

        service.saveAll(appId, list);
        return ResponseEntity.ok(service.getAllByAppId(appId));
    }

    // LOAD TABLE DATA
    @GetMapping("/assurance/{appId}")
    public ResponseEntity<List<AssuranceDetails>> getData(
            @PathVariable int appId) {

        return ResponseEntity.ok(service.getAllByAppId(appId));
    }
}