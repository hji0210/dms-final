package com.dms.datamodelmanagementserver.single.domain.serviceimpl;
import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainGroupDTO;
import com.dms.datamodelmanagementserver.single.domain.service.DomainGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DomainGroupServiceImpl implements DomainGroupService {

    private final ApiRequestBuilder<List<DomainGroupDTO>> apiRequestBuilder;



    public DomainGroupServiceImpl(ApiRequestBuilder<List<DomainGroupDTO>> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }
    @Override
    public List<DomainGroupDTO> getDomainGroup() {
        return apiRequestBuilder.setUrl("/std/single-domain/group")
                .setResponseType(new ParameterizedTypeReference<List<DomainGroupDTO>>() { })
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }

}
