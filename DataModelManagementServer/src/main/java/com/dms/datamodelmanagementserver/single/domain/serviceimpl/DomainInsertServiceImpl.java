package com.dms.datamodelmanagementserver.single.domain.serviceimpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.service.DomainInsertService;
import com.google.common.net.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Slf4j
@Service
public class DomainInsertServiceImpl implements DomainInsertService {
    private final ApiRequestBuilder<Boolean> apiRequestBuilder;

    public DomainInsertServiceImpl(ApiRequestBuilder<Boolean> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }

    @Override
    public boolean singleDomainInsertRest(DomainDTO domainDTO) {
        log.info("singleDomainInsertRest: {}", domainDTO);

        return apiRequestBuilder.setUrl("/std/single-domain/insert")
                .setObject(domainDTO)
                .setResponseType(Boolean.class)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();

    }


}