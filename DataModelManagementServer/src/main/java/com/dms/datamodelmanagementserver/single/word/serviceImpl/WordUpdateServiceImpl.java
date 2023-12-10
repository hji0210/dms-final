package com.dms.datamodelmanagementserver.single.word.serviceImpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;
import com.dms.datamodelmanagementserver.single.word.service.WordUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WordUpdateServiceImpl implements WordUpdateService {

    private final ApiRequestBuilder<Boolean> apiRequestBuilder;

    public WordUpdateServiceImpl(ApiRequestBuilder<Boolean> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }


    @Override
    public boolean singleWordUpdateRest(WordDTO wordDTO) {


        log.info("DMS::: SINGLEWORD UPDATE SERVISIMPL", wordDTO); // 로깅 추가



        return apiRequestBuilder.setUrl("/std/single-word/update")
                .setObject(wordDTO)
                .setResponseType(Boolean.class)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();

    }

}
