package com.dms.datamodelmanagementserver.single.term.serviceImpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.single.term.dto.TermDTO;
import com.dms.datamodelmanagementserver.single.term.service.TermInsertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TermInsertServiceImpl implements TermInsertService {

    private final ApiRequestBuilder<Boolean> apiRequestBuilder;

    public TermInsertServiceImpl(ApiRequestBuilder<Boolean> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }


    @Override
    public boolean singleTermInsertRest(List<TermDTO> termDTOList) {
        log.info("DMS::TermSearchServiceImpl::START stdAreaId = " + termDTOList);




        return apiRequestBuilder.setUrl("/std/single-term/insert")
                .setObject(termDTOList)
                .setResponseType(Boolean.class)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();

    }


}


