package com.dms.datamodelmanagementserver.global;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UrlBuilder {

    private static final String BASE_URL = "http://localhost:8072"; // 기본 URL

    public String buildServiceUrl(String path) {
        return UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .path(path) // 동적 경로 추가
                .toUriString(); // 최종 URL 문자열 반환
    }
}