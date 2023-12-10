package com.dms.datamodelmanagementserver.single.term.serviceImpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.single.term.dto.TermDomainDTO;
import com.dms.datamodelmanagementserver.single.term.service.TermInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TermInfoServiceImpl implements TermInfoService {

    private final ApiRequestBuilder<TermDomainDTO> apiRequestBuilder;

    public TermInfoServiceImpl(ApiRequestBuilder<TermDomainDTO> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }


    @Override
    public TermDomainDTO getTermInfoRest(String dicId) {
        log.info("DMS:TermInfoServiceImpl:::id= " + dicId); // 로깅 추가

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("dicId", dicId);



        return apiRequestBuilder.setUrl("/std/single-term/selectTerm")
                .setObject(requestMap)
                .setResponseType(TermDomainDTO.class)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();

    }



}
