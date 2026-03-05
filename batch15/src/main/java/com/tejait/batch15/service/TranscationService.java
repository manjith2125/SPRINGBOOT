package com.tejait.batch15.service;

import com.tejait.batch15.model.Transcation;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TranscationService {

    void uploadCsv(Integer appId, MultipartFile file);

    void saveAll(Integer appId, List<Transcation> list);

    List<Transcation> getAllByAppId(Integer appId);
}