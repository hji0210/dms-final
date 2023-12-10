package com.dms.datamodelmanagementserver.single.term.serviceImpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.single.term.service.TermSearchService;
import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;
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
public class TermSearchServiceImpl implements TermSearchService {

    private final ApiRequestBuilder<List<WordDTO>> apiRequestBuilder;

    public TermSearchServiceImpl(ApiRequestBuilder<List<WordDTO>> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }


    @Override
    public List<WordDTO> selectWordListRest(String stdAreaId, String searchWord) {
        log.info("DMS::TermSearchServiceImpl::START stdAreaId = " + stdAreaId);
        log.info("DMS::TermSearchServiceImpl::= searchWord " + searchWord);

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("stdAreaId", stdAreaId);
        requestMap.put("searchWord", searchWord);

        return apiRequestBuilder.setUrl("/std/single-term/search")
                .setObject(requestMap)
                .setResponseType(new ParameterizedTypeReference<List<WordDTO>>() {})
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();

    }


}
