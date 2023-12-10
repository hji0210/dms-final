package com.dms.datamodelmanagementserver.single.word.serviceImpl;


import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.single.word.service.WordAndTermDeleteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class WordAndTermDeleteServiceImpl implements WordAndTermDeleteService {

    private final ApiRequestBuilder<Boolean> apiRequestBuilder;


    public WordAndTermDeleteServiceImpl(ApiRequestBuilder<Boolean> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }

    @Override
    public boolean deleteWordAndTermRest(String deleteDicId) {

        log.info("DMS deleteWordAndTermRest ServiceImpl= " + deleteDicId);
        Map<String, String> requestMap = new HashMap<>();

        requestMap.put("dicId", deleteDicId);

        return apiRequestBuilder.setUrl("/std/single-wordAndTerm/delete")
                .setObject(requestMap)
                .setResponseType(Boolean.class)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }

}
