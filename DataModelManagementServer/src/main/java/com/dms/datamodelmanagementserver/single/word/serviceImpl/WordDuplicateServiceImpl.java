package com.dms.datamodelmanagementserver.single.word.serviceImpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;
import com.dms.datamodelmanagementserver.single.word.service.WordDuplicateService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WordDuplicateServiceImpl implements WordDuplicateService {

    private final ApiRequestBuilder<Boolean> apiRequestBuilder;

    public WordDuplicateServiceImpl(ApiRequestBuilder<Boolean> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }


    @Override
    public boolean isDuplicateRest(WordDTO wordDTO) {
        log.info(this.getClass().getName() + "::isDuplicateImple::START");


        log.info("DMS isDuplicateRest ServiceImpl= " + wordDTO);

//        Map<String, String> requestData = new HashMap<>();
//        requestData.put("logicalName", logicalName);
//        requestData.put("physicalName", physicalName);


        return apiRequestBuilder.setUrl("/std/single-word/duplicate")
                .setObject(wordDTO)
                .setResponseType(Boolean.class)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }


}
