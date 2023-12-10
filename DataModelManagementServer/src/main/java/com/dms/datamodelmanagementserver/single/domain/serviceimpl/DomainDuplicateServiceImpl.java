package com.dms.datamodelmanagementserver.single.domain.serviceimpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainGroupDTO;
import com.dms.datamodelmanagementserver.single.domain.service.DomainDuplicateService;
import com.google.common.net.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DomainDuplicateServiceImpl implements DomainDuplicateService {

    private final ApiRequestBuilder<Boolean> apiRequestBuilder;

    public DomainDuplicateServiceImpl(ApiRequestBuilder<Boolean> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }

    @Override
    public boolean isDuplicateDomainRest(DomainDTO domainDTO) {
        log.info(this.getClass().getName() + "::isDuplicateImple::START");
        log.info("DMS isDuplicateRest ServiceImpl= " + domainDTO);

        return apiRequestBuilder.setUrl("/std/single-domain/duplicate")
                .setObject(domainDTO)
                .setResponseType(Boolean.class)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }

    @Override
    public boolean isDuplicateDomainGroupRest(DomainGroupDTO domainGroupDTO) {
        log.info(this.getClass().getName() + "::isDuplicateImple::START");
        log.info("DMS isDuplicateDomainGroupRest ServiceImpl= " + domainGroupDTO);

        return apiRequestBuilder.setUrl("/std/single-domainGroup/duplicate")
                .setObject(domainGroupDTO)
                .setResponseType(Boolean.class)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }
}
