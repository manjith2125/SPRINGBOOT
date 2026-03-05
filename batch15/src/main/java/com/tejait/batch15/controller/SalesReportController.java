package com.tejait.batch15.controller;

import com.tejait.batch15.model.SalesReportDetails;
import com.tejait.batch15.service.SalesReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/loans")
@CrossOrigin(origins = "*")
public class SalesReportController {

    private final SalesReportService service;

    @PostMapping("/readExcel")
    public ResponseEntity<List<SalesReportDetails>> uploadExcel(
            @RequestParam("file") MultipartFile file,
            @RequestHeader(value = "Referer", defaultValue = "") String referer) {

        int appId = 1;
        if (referer.contains("id=")) {
            try {
                appId = Integer.parseInt(referer.split("id=")[1].split("&")[0]);
            } catch (Exception e) {
                appId = 1;
            }
        }

        service.uploadExcel(appId, file);
        return ResponseEntity.ok(service.getAllByAppId(appId));
    }

    @PostMapping("/saveSalesReport/{appId}")
    public ResponseEntity<List<SalesReportDetails>> saveChanges(
            @PathVariable int appId,
            @RequestBody List<SalesReportDetails> list) {

        service.saveAll(appId, list);
        return ResponseEntity.ok(service.getAllByAppId(appId));
    }

    @GetMapping("/salesReport/{appId}")
    public ResponseEntity<List<SalesReportDetails>> getData(@PathVariable int appId) {
        return ResponseEntity.ok(service.getAllByAppId(appId));
    }
}
