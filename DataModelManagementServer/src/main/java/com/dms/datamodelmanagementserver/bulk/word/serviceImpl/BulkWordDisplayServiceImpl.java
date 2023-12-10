package com.dms.datamodelmanagementserver.bulk.word.serviceImpl;

import com.dms.datamodelmanagementserver.bulk.word.dto.WordExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.word.service.BulkWordDisplayService;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class BulkWordDisplayServiceImpl implements BulkWordDisplayService {

    private final ApiRequestBuilder<List<WordExcelDataDTO>> apiRequestBuilder;
    private final int START_ROW = 1;

    public BulkWordDisplayServiceImpl(ApiRequestBuilder<List<WordExcelDataDTO>> apiRequestBuilder) {
        this.apiRequestBuilder = apiRequestBuilder;
    }

    @Override
    public List<WordExcelDataDTO> readWordExcelData(MultipartFile file, String stdAreaName) {
        List<WordExcelDataDTO> wordExcelDataDTOs = new ArrayList<>();
        if (file.isEmpty()) {
            return wordExcelDataDTOs;
        }
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Stream<Row> rowStream = StreamSupport.stream(sheet.spliterator(), false);
            wordExcelDataDTOs = rowStream
                    .filter(row -> !row.getCell(0).getStringCellValue().equals("") && row.getRowNum() >= START_ROW)
                    .map(row -> {
                        WordExcelDataDTO.WordExcelDataDTOBuilder builder = WordExcelDataDTO.builder();

                        builder.stdAreaId(stdAreaName);

                        if (row.getCell(0) != null) {
                            builder.dicLogicalName(row.getCell(0).getStringCellValue().trim());
                        }

                        if (row.getCell(1) != null) {
                            builder.dicPhysicalName(row.getCell(1).getStringCellValue().trim());
                        }

                        if (row.getCell(2) != null) {
                            builder.dicPhysicalFullName(row.getCell(2).getStringCellValue().trim());
                        }

                        if (row.getCell(3) != null) {
                            builder.entitySuffix(row.getCell(3).getStringCellValue().trim());
                        }

                        if (row.getCell(4) != null) {
                            builder.attributeSuffix(row.getCell(4).getStringCellValue().trim());
                        }

                        if (row.getCell(5) != null) {
                            builder.synonym(extractSynonyms(row.getCell(5).getStringCellValue().trim()));
                        }

                        if (row.getCell(6) != null) {
                            builder.dicDescription(row.getCell(6).getStringCellValue().trim());
                        }

                        if (row.getCell(7) != null) {
                            builder.reason(Collections.singletonList(row.getCell(7).getStringCellValue().trim()));
                        }
                        return builder.build();
                    })
                    .collect(Collectors.toList());

            return apiRequestBuilder.setUrl("/std/bulk-word/validate")
                    .setObject(wordExcelDataDTOs)
                    .setResponseType(new ParameterizedTypeReference<List<WordExcelDataDTO>>() {})
                    .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                    .execute();
            
        } catch (IOException e) {
            return wordExcelDataDTOs;
        }
    }

    private List<String> extractSynonyms(String synonym) {
        return Arrays.asList(synonym.split(",")).stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
