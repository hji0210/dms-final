package com.dms.datamodelmanagementserver.single.term.serviceImpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.single.term.service.TermUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TermUpdateServiceImpl implements TermUpdateService {

    private final ApiRequestBuilder<Boolean> apiRequestBuilder;


    public TermUpdateServiceImpl(ApiRequestBuilder<Boolean> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }


    @Override
    public boolean updateTermRest (String dicId, String domId, String dicDesc) {

        Map<String, String> requestMap = new HashMap<>();

        requestMap.put("dicId", dicId);
        requestMap.put("domId", domId);
        requestMap.put("dicDesc", dicDesc);


        return apiRequestBuilder.setUrl("/std/single-term/update")
                .setObject(requestMap)
                .setResponseType(Boolean.class)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();


    }

}
