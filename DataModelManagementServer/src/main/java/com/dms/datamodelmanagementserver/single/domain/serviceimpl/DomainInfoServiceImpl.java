package com.dms.datamodelmanagementserver.single.domain.serviceimpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.service.DomainInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class DomainInfoServiceImpl implements DomainInfoService {


    private final ApiRequestBuilder<DomainDTO> apiRequestBuilder;

    public DomainInfoServiceImpl(ApiRequestBuilder<DomainDTO> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }


    @Override
    public DomainDTO getDomainInfoRest(String domId) {
        log.info("DMS:GetDomainInfoRest:::" + domId);


        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("domId", domId);


        return apiRequestBuilder.setUrl("/std/single-domain/select")
                .setObject(requestMap)
                .setResponseType(DomainDTO.class)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();

    }


}