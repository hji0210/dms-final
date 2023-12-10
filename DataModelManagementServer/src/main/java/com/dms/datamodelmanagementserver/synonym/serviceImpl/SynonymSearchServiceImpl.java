package com.dms.datamodelmanagementserver.synonym.serviceImpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.synonym.dto.SynonymDTO;
import com.dms.datamodelmanagementserver.synonym.service.SynonymSearchService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SynonymSearchServiceImpl implements SynonymSearchService {

    private final ApiRequestBuilder<List<SynonymDTO>> apiRequestBuilder;

    public SynonymSearchServiceImpl(ApiRequestBuilder<List<SynonymDTO>> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }

    @Override
    public List<SynonymDTO> selectList(SynonymDTO synonymDTO) {
        return apiRequestBuilder.setUrl("/std/synonym/searchList")
                .setObject(synonymDTO)
                .setResponseType(new ParameterizedTypeReference<List<SynonymDTO>>() { })
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }
}
