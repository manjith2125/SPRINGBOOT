package com.tejait.batch15.service;

import com.tejait.batch15.model.SalesReportDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SalesReportService {

    void uploadExcel(int appId, MultipartFile file);

    void saveAll(int appId, List<SalesReportDetails> list);

    List<SalesReportDetails> getAllByAppId(int appId);
}
