package com.tejait.batch15.service;

import com.tejait.batch15.model.ApplicationOverview;

public interface ApplicationOverviewService {

    ApplicationOverview getOverviewByAppId(int appId);
}