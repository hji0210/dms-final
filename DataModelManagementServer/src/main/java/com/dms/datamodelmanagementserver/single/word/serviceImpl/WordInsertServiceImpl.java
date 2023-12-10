package com.dms.datamodelmanagementserver.single.word.serviceImpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;
import com.dms.datamodelmanagementserver.single.word.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Slf4j
@Service
public class WordInsertServiceImpl implements WordService {

    private final ApiRequestBuilder<Boolean> apiRequestBuilder;

    public WordInsertServiceImpl(ApiRequestBuilder<Boolean> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }


    @Override
    public boolean singleWordInsertRest(WordDTO wordDTO) {
        log.info("singlewordInsertRest: {}", wordDTO); // 로깅 추가



        return apiRequestBuilder.setUrl("/std/single-word/insert")
                .setObject(wordDTO)
                .setResponseType(Boolean.class)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();

    }



}
