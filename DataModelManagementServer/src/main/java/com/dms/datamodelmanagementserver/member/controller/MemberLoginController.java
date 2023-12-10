package com.dms.datamodelmanagementserver.member.controller;

import com.dms.datamodelmanagementserver.global.DataType;
import com.dms.datamodelmanagementserver.global.DomainType;
import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.global.UrlBuilder;
import com.dms.datamodelmanagementserver.global.session.service.SessionService;
import com.dms.datamodelmanagementserver.member.dto.MemberDTO;
import com.dms.datamodelmanagementserver.member.service.MemberLoginService;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectListService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dms")
public class MemberLoginController {
    private final MemberLoginService memberLoginService;
    private final UrlBuilder urlBuilder;

    private final StandardAreaSelectListService standardAreaSelectListService;
    private final LogDefault logDefault;

    public MemberLoginController(MemberLoginService memberLoginService, UrlBuilder urlBuilder, StandardAreaSelectListService standardAreaSelectListService, LogDefault logDefault, SessionService sessionService) {
        this.memberLoginService = memberLoginService;
        this.urlBuilder = urlBuilder;
        this.standardAreaSelectListService = standardAreaSelectListService;
        this.logDefault = logDefault;
    }

    @GetMapping(value = "/")
    public String welcomePage() {
        logDefault.logCurrentMethod();
        return "/member/auth-login-basic";
    }

    @GetMapping(value = "/dashboard")
    public String mainPage(HttpSession session) {
        logDefault.logCurrentMethod();
        List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList();
        session.setAttribute("stdList", stdList);

        return "/dashboard/dashboard";
    }

    @PostMapping(value = "/login")
    public String authenticateMember(@ModelAttribute MemberDTO memberDTO, Model model, HttpSession session) {
        logDefault.logCurrentMethod();

        List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList();
        session.setAttribute("stdList", stdList);
        model.addAttribute("domainTypes", Arrays.stream(DomainType.values())
                .map(Enum::toString)
                .collect(Collectors.toList()));
        model.addAttribute("dataTypes", Arrays.stream(DataType.values())
                .map(Enum::toString)
                .collect(Collectors.toList()));

        MemberDTO resultMemberDTO = memberLoginService.authenticateMember(memberDTO);
        if (resultMemberDTO == null) {
            String serviceUrl = urlBuilder.buildServiceUrl("/dms/");
            return String.format("redirect:%s", serviceUrl);
        }
        session.setAttribute("member", resultMemberDTO);
        return "/standardData/standardData";
    }
}
