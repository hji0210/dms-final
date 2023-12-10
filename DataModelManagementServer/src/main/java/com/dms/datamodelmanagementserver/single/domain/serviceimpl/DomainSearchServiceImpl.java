package com.dms.datamodelmanagementserver.single.domain.serviceimpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.service.DomainSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DomainSearchServiceImpl implements DomainSearchService {

    private final ApiRequestBuilder<List<DomainDTO>> apiRequestBuilder;
    public DomainSearchServiceImpl(ApiRequestBuilder<List<DomainDTO>> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }

    @Override
    public List<DomainDTO> searchDomainNameByDomainNameRest(String stdAreaId, String domainName) {
        log.info("DMS::searchDomainNameByDomainNameRest:: stdAreaId = " + stdAreaId);
        log.info("DMS::searchDomainNameByDomainNameRest::= domainName " + domainName);

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("stdAreaId", stdAreaId);
        requestMap.put("domainName", domainName);

        return apiRequestBuilder.setUrl("/std/single-domain/searchDomain")
                .setObject(requestMap)
                .setResponseType(new ParameterizedTypeReference<List<DomainDTO>>() {})
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();

    }


}
