package com.dms.datamodelmanagementserver.synonym.controller;

import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectListService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/dms/synonym")
public class SynonymPageController {

    private final StandardAreaSelectListService standardAreaSelectListService;

    private final LogDefault logDefault;

    public SynonymPageController(StandardAreaSelectListService standardAreaSelectListService, LogDefault logDefault) {
        this.standardAreaSelectListService = standardAreaSelectListService;
        this.logDefault = logDefault;
    }


    @GetMapping(value = "/page")
    public String synonymSelectPage(Model model, HttpSession session) {
        logDefault.logCurrentMethod();
        List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList();//추가
        session.setAttribute("stdList",stdList);//추가
        return "/synonym/synonymSelect";
    }
}
