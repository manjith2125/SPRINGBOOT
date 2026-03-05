package com.tejait.batch15.serviceimpl;

import tools.jackson.databind.ObjectMapper;
import com.tejait.batch15.model.AssuranceDetails;
import com.tejait.batch15.repository.AssuranceDetailsRepository;
import com.tejait.batch15.service.AssuranceDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AssuranceDetailsServiceImpl implements AssuranceDetailsService {

    private final AssuranceDetailsRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // ========= JSON UPLOAD =========
    @Override
    @Transactional
    public void saveFromJson(int appId, MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file is empty or null");
        }

        // prevent duplicate upload
        List<AssuranceDetails> existing =
                repository.findAllByAppId(appId);

        if (!existing.isEmpty()) {
            log.info("Data already exists for appId: {}, skipping upload", appId);
            return;
        }

        try {

            List<AssuranceDetails> list = Arrays.asList(
                    objectMapper.readValue(
                            file.getInputStream(),
                            AssuranceDetails[].class
                    )
            );

            list.forEach(d -> {
                d.setAppId(appId);
                d.setId(0);
            });

            repository.saveAll(list);

            log.info("Successfully uploaded {} records for appId: {}",
                    list.size(), appId);

        } catch (IOException e) {
            log.error("JSON upload failed for appId: {}", appId, e);
            throw new RuntimeException("JSON upload failed", e);
        }
    }

    // ========= SAVE CHANGES =========
    @Override
    @Transactional
    public void saveAll(int appId, List<AssuranceDetails> list) {

        if (list == null || list.isEmpty()) {
            log.warn("saveAll called with empty list for appId: {}", appId);
            return;
        }

        list.forEach(d -> d.setAppId(appId));

        repository.saveAll(list);

        log.info("Saved {} records for appId: {}", list.size(), appId);
    }

    @Override
    public List<AssuranceDetails> getAllByAppId(int appId) {
        return repository.findAllByAppId(appId);
    }
}