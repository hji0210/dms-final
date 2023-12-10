package com.dms.datamodelmanagementserver.bulk.domain.serviceImpl;

import com.dms.datamodelmanagementserver.bulk.domain.dto.DomainExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.domain.service.BulkDomainDisplayService;
import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
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

@Slf4j
@Service
public class BulkDomainDisplayServiceImpl implements BulkDomainDisplayService {

    private final ApiRequestBuilder<List<DomainExcelDataDTO>> apiRequestBuilder;
    private final int START_ROW = 1;

    @Autowired
    public BulkDomainDisplayServiceImpl(ApiRequestBuilder<List<DomainExcelDataDTO>> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }

    @Override
    public List<DomainExcelDataDTO> readDomainExcelData(MultipartFile file, String stdAreaName) {
        List<DomainExcelDataDTO> domainExcelDataList = new ArrayList<>();
        List<DomainExcelDataDTO> domainExcelDataDTOList;
        if (file.isEmpty()) {
            return domainExcelDataList;
        }
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            Stream<Row> rowStream = StreamSupport.stream(sheet.spliterator(), false);
            domainExcelDataList = rowStream
                    .filter(row -> !row.getCell(0).getStringCellValue().equals("") && row.getRowNum() >= START_ROW)
                    .map(row -> {
                        DomainExcelDataDTO.DomainExcelDataDTOBuilder builder = DomainExcelDataDTO.builder();

                        builder.stdAreaId(stdAreaName);

                        if (row.getCell(0) != null) {
                            builder.keyDomainName(row.getCell(0).getStringCellValue().trim());
                        }

                        if (row.getCell(1) != null) {
                            builder.domainName(row.getCell(1).getStringCellValue().trim());
                        }

                        if (row.getCell(2) != null) {
                            builder.domainTypeCode(row.getCell(2).getStringCellValue().trim());
                        }

                        if (row.getCell(3) != null) {
                            builder.domainGroupName(row.getCell(3).getStringCellValue().trim());
                        }

                        if (row.getCell(4) != null) {
                            builder.dataTypeCode(row.getCell(4).getStringCellValue().trim());
                        }

                        if (row.getCell(5) != null) {
                            builder.dataLength((int) row.getCell(5).getNumericCellValue());
                        }

                        if (row.getCell(6) != null) {
                            builder.dataScale((int) row.getCell(6).getNumericCellValue());
                        }

                        if (row.getCell(7) != null) {
                            builder.dataMin((int) row.getCell(7).getNumericCellValue());
                        }

                        if (row.getCell(8) != null) {
                            builder.dataMax((int) row.getCell(8).getNumericCellValue());
                        }

                        if (row.getCell(9) != null) {
                            builder.domainDescription(row.getCell(9).getStringCellValue().trim());
                        }

                        if (row.getCell(10) != null) {
                            builder.reason(Collections.singletonList(row.getCell(10).getStringCellValue().trim()));
                        }
                        return builder.build();
                    })
                    .collect(Collectors.toList());

            domainExcelDataDTOList = apiRequestBuilder.setUrl("/std/bulk-domain/validate")
                    .setObject(domainExcelDataList)
                    .setResponseType(new ParameterizedTypeReference<List<DomainExcelDataDTO>>() {})
                    .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                    .execute();
            return domainExcelDataDTOList;

        } catch (IOException e) {
            return domainExcelDataList;
        }
    }
}