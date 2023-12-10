package com.dms.datamodelmanagementserver.single.domain.controller;

import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.global.UrlBuilder;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainGroupDTO;
import com.dms.datamodelmanagementserver.single.domain.service.DomainDuplicateService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/dms")
public class DomainDuplicateController {

    private final DomainDuplicateService domainDuplicateService;
    private final UrlBuilder urlBuilder;

    private final LogDefault logDefault;


    public DomainDuplicateController(DomainDuplicateService domainDuplicateService, UrlBuilder urlBuilder,LogDefault logDefault) {
        this.domainDuplicateService = domainDuplicateService;
        this.urlBuilder = urlBuilder;
        this.logDefault=logDefault;
    }

    @PostMapping("/single-domain/duplicateDomainRest")
    @ResponseBody
    public boolean isDuplicateDomainRest(@RequestBody DomainDTO domainDTO, HttpServletResponse response) { // Jackson 라이브러리가 json string을 dto로 받아줌
        System.out.println(this.getClass().getName() + "DMS::DomainDuplicateController()::START");

        // 중복 체크 로직을 수행하고 중복 여부를 판단합니다.
        boolean isDuplicate = domainDuplicateService.isDuplicateDomainRest(domainDTO);

        System.out.println(this.getClass().getName() + "DMS::DomainDuplicateController()::END");
        log.info(this.getClass().getName() + "DMS::DomainDuplicateController= " + isDuplicate);
        // 중복 여부를 클라이언트로 반환
        return isDuplicate;
    }

    @PostMapping("/single-domain/duplicateDomainGroupRest")
    @ResponseBody
    public boolean isDuplicateDomainGroupRest(@RequestBody DomainGroupDTO domainGroupDTO, HttpServletResponse response) {
        System.out.println(this.getClass().getName() + "DMS::DomainDuplicateController()::START");

        // 중복 체크 로직을 수행하고 중복 여부를 판단합니다.
        boolean isDuplicate = domainDuplicateService.isDuplicateDomainGroupRest(domainGroupDTO);

        System.out.println(this.getClass().getName() + "DMS::DomainDuplicateController()::END");
        log.info(this.getClass().getName() + "DMS::DomainDuplicateController= " + isDuplicate);
        // 중복 여부를 클라이언트로 반환
        return isDuplicate;
    }
}