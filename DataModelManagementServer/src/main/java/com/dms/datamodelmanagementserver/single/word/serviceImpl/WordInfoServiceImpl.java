package com.dms.datamodelmanagementserver.single.word.serviceImpl;


import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;
import com.dms.datamodelmanagementserver.single.word.service.WordInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class WordInfoServiceImpl implements WordInfoService {


    private final ApiRequestBuilder<WordDTO> apiRequestBuilder;

    public WordInfoServiceImpl(ApiRequestBuilder<WordDTO> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }


    @Override
    public WordDTO getWordAndTermInfoRest(String dicId) {
        log.info("DMS:GetWordAndTermInfoRest:::" + dicId); // 로깅 추가

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("dicId", dicId);



        return apiRequestBuilder.setUrl("/std/single-wordAndTerm/select")
                .setObject(requestMap)
                .setResponseType(WordDTO.class)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();

    }


}
