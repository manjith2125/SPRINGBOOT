package com.tejait.batch15.controller;

import com.tejait.batch15.model.ApplicationOverview;
import com.tejait.batch15.service.ApplicationOverviewService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@RequestMapping("/loans")
public class ApplicationOverviewController {


     ApplicationOverviewService overviewService;

    @GetMapping("getOverviewDeatils/{appId}")
    public ApplicationOverview getOverview(@PathVariable int appId) {

        return overviewService.getOverviewByAppId(appId);
    }
}