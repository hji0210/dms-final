package com.dms.datamodelmanagementserver.bulk.term.serviceImpl;

import com.dms.datamodelmanagementserver.bulk.term.dto.BulkTermExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.term.service.BulkTermDisplayService;
import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class BulkTermDisplayServiceImpl implements BulkTermDisplayService {

    private final ApiRequestBuilder<List<BulkTermExcelDataDTO>> apiRequestBuilder;
    private final int START_ROW = 1;

    public BulkTermDisplayServiceImpl(ApiRequestBuilder<List<BulkTermExcelDataDTO>> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }

    @Override
    public List<BulkTermExcelDataDTO> readTermExcelData(MultipartFile file, String stdAreaName) {
        List<BulkTermExcelDataDTO> bulkTermExcelDataDTOList = new ArrayList<>();
        if (file.isEmpty()) {
            return bulkTermExcelDataDTOList;
        }
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Stream<Row> rowStream = StreamSupport.stream(sheet.spliterator(), false);
            bulkTermExcelDataDTOList = rowStream
                    .filter(row -> !row.getCell(0).getStringCellValue().equals("") && row.getRowNum() >= START_ROW)
                    .map(row -> {
                        BulkTermExcelDataDTO.BulkTermExcelDataDTOBuilder builder = BulkTermExcelDataDTO.builder();

                        builder.stdAreaId(stdAreaName);

                        if (row.getCell(0) != null) {
                            builder.tempDicLogicalName(row.getCell(0).getStringCellValue().trim());
                        }

                        if (row.getCell(1) != null) {
                            builder.dicDescription(row.getCell(1).getStringCellValue().trim());
                        }

                        if (row.getCell(2) != null) {
                            builder.keyDomainName(row.getCell(2).getStringCellValue().trim());
                        }

                        if (row.getCell(3) != null) {
                            builder.domainTypeCode(row.getCell(3).getStringCellValue().trim());
                        }

                        if (row.getCell(4) != null) {
                            builder.domainGroupName(row.getCell(4).getStringCellValue().trim());
                        }

                        if (row.getCell(5) != null) {
                            builder.dataTypeCode(row.getCell(5).getStringCellValue().trim());
                        }

                        if (row.getCell(6) != null) {
                            builder.dataLength((int) row.getCell(6).getNumericCellValue());
                        }

                        if (row.getCell(7) != null) {
                            builder.dataScale((int) row.getCell(7).getNumericCellValue());
                        }

                        if (row.getCell(8) != null) {
                            builder.dicLogicalName(row.getCell(8).getStringCellValue().trim());
                        }

                        if (row.getCell(9) != null) {
                            builder.dicPhysicalName(row.getCell(9).getStringCellValue().trim());
                        }

                        if (row.getCell(10) != null) {
                            builder.domainName(row.getCell(10).getStringCellValue().trim());
                        }

                        if (row.getCell(11) != null) {
                            builder.reason(Collections.singletonList(row.getCell(11).getStringCellValue().trim()));
                        }

                        return builder.build();
                    })
                    .collect(Collectors.toList());

            return apiRequestBuilder.setUrl("/std/bulk-term/validate")
                    .setObject(bulkTermExcelDataDTOList)
                    .setResponseType(new ParameterizedTypeReference<List<BulkTermExcelDataDTO>>() {})
                    .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                    .execute();
            
        } catch (IOException e) {
            return bulkTermExcelDataDTOList;
        }
    }

}
