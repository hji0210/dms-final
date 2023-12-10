package com.dms.datamodelmanagementserver.single.word.controller;

import com.dms.datamodelmanagementserver.global.UrlBuilder;
import com.dms.datamodelmanagementserver.single.word.service.WordDuplicateService;
import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/dms")
public class WordDuplicateController {

    private final WordDuplicateService wordDuplicateService;
    private final UrlBuilder urlBuilder;

    public WordDuplicateController(WordDuplicateService wordDuplicateService, UrlBuilder urlBuilder) {
        this.wordDuplicateService = wordDuplicateService;
        this.urlBuilder = urlBuilder;
    }


    @PostMapping("/single-word/duplicateRest")
    @ResponseBody
    public Map<String, Boolean> isDuplicateRest(@RequestBody WordDTO wordDTO) {
        System.out.println("DMS::WordDuplicateController()::START");

        boolean isDuplicate = wordDuplicateService.isDuplicateRest(wordDTO);

        System.out.println("DMS::WordDuplicateController()::END");
        log.info("DMS::WordDuplicateController= " + isDuplicate);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        return response;
    }

}


