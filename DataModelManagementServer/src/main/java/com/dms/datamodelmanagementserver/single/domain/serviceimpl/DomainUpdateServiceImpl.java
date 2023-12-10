package com.dms.datamodelmanagementserver.single.domain.serviceimpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.service.DomainUpdateService;
import com.google.common.net.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Slf4j
@Service
public class DomainUpdateServiceImpl implements DomainUpdateService {

    private final ApiRequestBuilder<String> apiRequestBuilder;

    public DomainUpdateServiceImpl(ApiRequestBuilder<String> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }
    @Override
    public DomainDTO singleDomainUpdateRest(DomainDTO domainDTO) {
        log.info("singleDomainUpdateRest: {}", domainDTO);


        String apiResponse;
        try {
            apiResponse = apiRequestBuilder.setUrl("/std/single-domain/update")
                    .setObject(domainDTO)
                    .setResponseType(String.class)
                    .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                    .execute();
        } catch (RestClientException e) {
            log.error("Error while making the API request: {}", e.getMessage());
            return null;
        }



        DomainDTO responseDTO = convertApiResponseToDomainDTO(apiResponse);
        log.info("singleDomainUpdateRest: {}", responseDTO);

        return responseDTO;
    }


    private DomainDTO convertApiResponseToDomainDTO(String apiResponse) {


        return null;
    }
}
