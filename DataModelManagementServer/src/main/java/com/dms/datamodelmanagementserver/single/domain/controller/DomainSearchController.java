package com.dms.datamodelmanagementserver.single.domain.controller;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.service.DomainSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/dms")
public class DomainSearchController {

    private final DomainSearchService domainSearchService;

    public DomainSearchController(DomainSearchService domainSearchService) {
        this.domainSearchService = domainSearchService;
    }

    @PostMapping("/single-domain/domainSearchRest")
    @ResponseBody
    public Map<String, Object> getDomainListRest(@RequestBody Map<String, String> requestMap) {

        String stdAreaId = requestMap.get("stdAreaId");
        String domainName = requestMap.get("domainName");

        List<DomainDTO> domainList = domainSearchService.searchDomainNameByDomainNameRest(stdAreaId, domainName);


        Map<String, Object> response = new HashMap<>();

        response.put("domainList", domainList);


        return response;

    }

}
