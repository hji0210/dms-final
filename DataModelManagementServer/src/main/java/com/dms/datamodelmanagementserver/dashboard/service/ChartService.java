package com.dms.datamodelmanagementserver.dashboard.service;

import com.dms.datamodelmanagementserver.dashboard.dto.ChartDTO;

import java.util.List;

public interface ChartService {
    List<ChartDTO> getChart(String standardAreaName);
}
