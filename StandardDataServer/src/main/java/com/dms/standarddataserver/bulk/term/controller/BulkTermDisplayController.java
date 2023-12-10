package com.dms.standarddataserver.bulk.term.controller;

import com.dms.standarddataserver.bulk.term.dto.BulkTermExcelDataDTO;
import com.dms.standarddataserver.bulk.term.service.BulkTermDisplayService;
import com.dms.standarddataserver.global.LogDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/std")
public class BulkTermDisplayController {

    private final BulkTermDisplayService bulkTermDisplayService;
    private final LogDefault logDefault;

    public BulkTermDisplayController(BulkTermDisplayService bulkTermDisplayService, LogDefault logDefault) {
        this.bulkTermDisplayService = bulkTermDisplayService;
        this.logDefault = logDefault;
    }

    @PostMapping("/bulk-term/validate")
    private ResponseEntity<?> validateBulkTerm(@RequestBody List<BulkTermExcelDataDTO> bulkTermExcelDataDTOList) {
        logDefault.logCurrentMethod();
        List<BulkTermExcelDataDTO> bulkTermExcelDataDTOs = bulkTermDisplayService.validateBulkTerm(bulkTermExcelDataDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(bulkTermExcelDataDTOs);
    }
}
