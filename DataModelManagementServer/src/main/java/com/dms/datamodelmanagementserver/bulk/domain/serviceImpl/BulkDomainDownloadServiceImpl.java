package com.dms.datamodelmanagementserver.bulk.domain.serviceImpl;

import com.dms.datamodelmanagementserver.bulk.domain.dto.DomainExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.domain.service.BulkDomainDownloadService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class BulkDomainDownloadServiceImpl implements BulkDomainDownloadService {
    @Override
    public String saveDomainDataToExcel(List<DomainExcelDataDTO> domainExcelDataDTOList, HttpServletResponse response) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("Sheet1");

            Row headerRow = sheet.createRow(0);
            String[] headers = {"번호", "대표도메인명", "도메인명", "도메인유형", "도메인그룹",
                    "논리데이터타입", "데이터길이", "소수점", "최소값", "최대값", "설명", "사유"};
            IntStream.range(0, headers.length)
                    .forEach(index -> headerRow.createCell(index).setCellValue(headers[index]));

            IntStream.range(0, domainExcelDataDTOList.size())
                    .forEach(index -> {
                        Row dataRow = sheet.createRow(index + 1);
                        DomainExcelDataDTO data = domainExcelDataDTOList.get(index);

                        dataRow.createCell(0).setCellValue(++index);
                        dataRow.createCell(1).setCellValue(data.getKeyDomainName());
                        dataRow.createCell(2).setCellValue(data.getDomainName());
                        dataRow.createCell(3).setCellValue(data.getDomainTypeCode());
                        dataRow.createCell(4).setCellValue(data.getDomainGroupName());
                        dataRow.createCell(5).setCellValue(data.getDataTypeCode());
                        dataRow.createCell(6).setCellValue(data.getDataLength());
                        dataRow.createCell(7).setCellValue(data.getDataScale());
                        dataRow.createCell(8).setCellValue(data.getDataMin());
                        dataRow.createCell(9).setCellValue(data.getDataMax());
                        dataRow.createCell(10).setCellValue(data.getDomainDescription());
                        dataRow.createCell(11).setCellValue(String.join("/", data.getReason()));
                    });

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();

            return Base64.getEncoder().encodeToString(outputStream.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
