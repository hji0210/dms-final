package com.dms.datamodelmanagementserver.single.domain.controller;


import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.global.UrlBuilder;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainGroupDTO;
import com.dms.datamodelmanagementserver.single.domain.service.DomainGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/dms")
public class DomainGroupController {

    private final DomainGroupService domainGroupService;
    private final UrlBuilder urlBuilder;

    private final LogDefault logDefault;

    public DomainGroupController(DomainGroupService domainGroupService, UrlBuilder urlBuilder, LogDefault logDefault) {
        this.domainGroupService = domainGroupService;
        this.urlBuilder = urlBuilder;
        this.logDefault = logDefault;
    }


    // 도메인 그룹 조회 - getDomainGroup()
    @PostMapping("/single-domain/group")
    @ResponseBody
    public ResponseEntity<List<DomainGroupDTO>> domainGroup() {
        logDefault.logCurrentMethod();
        return ResponseEntity.status(HttpStatus.OK).body(domainGroupService.getDomainGroup());
    }

}