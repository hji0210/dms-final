package com.dms.datamodelmanagementserver.single.domain.serviceimpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.service.DomainDeleteService;
import com.google.common.net.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;

@Slf4j
@Service
public class DomainDeleteServiceImpl implements DomainDeleteService {

    private final ApiRequestBuilder<Boolean> apiRequestBuilder;

    public DomainDeleteServiceImpl(ApiRequestBuilder<Boolean> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }


    @Override
    public boolean deleteDomain(DomainDTO domainDTO) {
        log.info("DMS deleteDomainRest ServiceImpl= " + domainDTO);

        return apiRequestBuilder.setUrl("/std/single-domain/delete")
                .setObject(domainDTO)
                .setResponseType(Boolean.class)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }


}
