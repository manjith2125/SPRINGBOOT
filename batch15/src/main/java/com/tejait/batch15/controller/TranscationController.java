package com.tejait.batch15.controller;

import com.tejait.batch15.model.Transcation;
import com.tejait.batch15.service.TranscationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/sales")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TranscationController {

    private final TranscationService service;

    // =============== Upload CSV =================

    @PostMapping("/readTransactionsCsv")
    public ResponseEntity<List<Transcation>> uploadCsv(
            @RequestParam("file") MultipartFile file,
            @RequestHeader(value = "Referer", defaultValue = "") String referer) {

        Integer appId = extractAppId(referer);

        service.uploadCsv(appId, file);

        return ResponseEntity.ok(service.getAllByAppId(appId));
    }

    // =============== Save Changes =================

    @PostMapping("/saveTxnsData/{appId}")
    public ResponseEntity<List<Transcation>> saveChanges(
            @PathVariable Integer appId,
            @RequestBody List<Transcation> list) {

        service.saveAll(appId, list);

        return ResponseEntity.ok(service.getAllByAppId(appId));
    }

    // =============== Fetch =================

    @GetMapping("/transactions/{appId}")
    public ResponseEntity<List<Transcation>> getAll(
            @PathVariable Integer appId) {

        return ResponseEntity.ok(service.getAllByAppId(appId));
    }

    private Integer extractAppId(String referer) {
        try {
            if (referer.contains("id=")) {
                return Integer.parseInt(
                        referer.split("id=")[1].split("&")[0]
                );
            }
        } catch (Exception ignored) {}
        return 1;
    }
}