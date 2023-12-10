package com.dms.datamodelmanagementserver.single.domain.controller;

import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.global.UrlBuilder;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.service.DomainUpdateService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@Controller
@RequestMapping("/dms")
public class DomainUpdateController {

    private final DomainUpdateService domainUpdateService;
    private final UrlBuilder urlBuilder;


    private final LogDefault logDefault;


    public DomainUpdateController(DomainUpdateService domainUpdateService, UrlBuilder urlBuilder,LogDefault logDefault) {
        this.domainUpdateService = domainUpdateService;
        this.urlBuilder = urlBuilder;
        this.logDefault = logDefault;
    }


    @PostMapping("/single-domain/updateRest")
    public String singleDomainUpdateRest(@RequestBody DomainDTO domainDTO, HttpServletResponse response, Model model) {
        System.out.println(this.getClass().getName() + "::singleDomainUpdateRest()::START");
        logDefault.logCurrentMethod();
        DomainDTO result = domainUpdateService.singleDomainUpdateRest(domainDTO);
        if (result == null) {
            model.addAttribute("errorMessage", "수정에 실패했습니다.");
        }
        model.addAttribute("successMessage", "수정에 성공했습니다."); // 실패 알람 메시지

        return "/standardData/standardData";
    }



}
