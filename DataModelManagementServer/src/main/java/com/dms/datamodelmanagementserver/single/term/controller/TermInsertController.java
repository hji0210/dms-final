package com.dms.datamodelmanagementserver.single.term.controller;


import com.dms.datamodelmanagementserver.single.term.dto.TermDTO;
import com.dms.datamodelmanagementserver.single.term.dto.TermWordDTO;
import com.dms.datamodelmanagementserver.single.term.service.TermInsertService;
import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;
import com.dms.datamodelmanagementserver.single.word.service.WordDuplicateService;
import com.dms.datamodelmanagementserver.single.word.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/dms")
public class TermInsertController {

    private final WordService wordService;
    private final TermInsertService termInsertService;
    private final WordDuplicateService wordDuplicateService;


    public TermInsertController(WordService wordService, TermInsertService termInsertService, WordDuplicateService wordDuplicateService, WordDuplicateService wordDuplicateService1) {
        this.wordService = wordService;
        this.termInsertService = termInsertService;
        this.wordDuplicateService = wordDuplicateService1;
    }


    @PostMapping("/single-term/insertRest")
    @ResponseBody
    public Map<String, Boolean> singleTermInsertRest(@RequestBody TermWordDTO termWordDTO) {
        // Handle the received WordDTO and TermDTOList
        log.info("Received termWordDTO: " + termWordDTO.getWordDTO());
        log.info("Received termWordDTO: " + termWordDTO.getTermDTOList());

        WordDTO wordDTO = termWordDTO.getWordDTO();

        wordDTO.setStandardYn("Y");
        wordDTO.setDicGbnCd("term");



        List<TermDTO> termDTOList = termWordDTO.getTermDTOList();

        String commonId = UUID.randomUUID().toString();

        wordDTO.setDicId(commonId);


        for (TermDTO termDTO : termDTOList) {
            termDTO.setTermId(commonId);
        }




        wordService.singleWordInsertRest(wordDTO);

        boolean isDuplicate = wordDuplicateService.isDuplicateRest(wordDTO);


        boolean inInserted = termInsertService.singleTermInsertRest(termDTOList);

        log.info("Received wordDTO and TermDTO ID result::: " + wordDTO.getDicId(), termDTOList.get(0).getTermId());


        // Perform insertion or required logic with the received data

        // Prepare a response
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        response.put("inInserted", inInserted); // Modify based on the operation's success or failure

        return response;
    }

}
