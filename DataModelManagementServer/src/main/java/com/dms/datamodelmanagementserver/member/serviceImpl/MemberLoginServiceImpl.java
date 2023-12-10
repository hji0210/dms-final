package com.dms.datamodelmanagementserver.member.serviceImpl;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.member.dto.MemberDTO;
import com.dms.datamodelmanagementserver.member.service.MemberLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberLoginServiceImpl implements MemberLoginService {
    private final ApiRequestBuilder<MemberDTO> apiRequestBuilder;

    public MemberLoginServiceImpl(ApiRequestBuilder<MemberDTO> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }

    @Override
    public MemberDTO authenticateMember(MemberDTO memberDTO) {
        return apiRequestBuilder.setUrl("/user/login")
                .setObject(memberDTO)
                .setResponseType(MemberDTO.class)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .execute();
    }
}

