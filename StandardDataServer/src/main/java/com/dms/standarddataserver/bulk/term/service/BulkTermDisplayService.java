package com.dms.standarddataserver.bulk.term.service;

import com.dms.standarddataserver.bulk.term.dto.BulkTermExcelDataDTO;

import java.util.List;

public interface BulkTermDisplayService {

    public List<BulkTermExcelDataDTO> validateBulkTerm(List<BulkTermExcelDataDTO> bulkTermDataDTOList);

}
