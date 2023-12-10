package com.dms.datamodelmanagementserver.dashboard.serviceImpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.dashboard.dto.ChartDTO;
import com.dms.datamodelmanagementserver.dashboard.service.ChartService;

import com.dms.datamodelmanagementserver.global.session.service.SessionService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartServiceImpl implements ChartService {
    private final ApiRequestBuilder<List<ChartDTO>> apiRequestBuilder;
    private final SessionService sessionService;

    public ChartServiceImpl(ApiRequestBuilder<List<ChartDTO>> apiRequestBuilder, SessionService sessionService) {
        this.apiRequestBuilder = apiRequestBuilder;
        this.sessionService = sessionService;
    }

    @Override
    public List<ChartDTO> getChart(String standardAreaName) {
        standardAreaName = standardAreaName.replaceAll("\"", "");
        if (standardAreaName.equals("FirstChartLoad")) {
            standardAreaName = sessionService.getSession();
        }
        return apiRequestBuilder
                .setUrl("/std/dashboard/chart")
                .setObject(standardAreaName)
                .setResponseType(new ParameterizedTypeReference<List<ChartDTO>>() {
                })
                .setMethod(HttpMethod.POST)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }

}
