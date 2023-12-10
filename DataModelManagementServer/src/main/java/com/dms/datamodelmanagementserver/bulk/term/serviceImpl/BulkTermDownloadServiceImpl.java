package com.dms.datamodelmanagementserver.bulk.term.serviceImpl;

import com.dms.datamodelmanagementserver.bulk.term.dto.BulkTermExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.term.service.BulkTermDownloadService;
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
public class BulkTermDownloadServiceImpl implements BulkTermDownloadService {
    @Override
    public String saveTermDataToExcel(List<BulkTermExcelDataDTO> bulkTermExcelDataDTOList, HttpServletResponse response) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("Sheet1");

            Row headerRow = sheet.createRow(0);
            String[] headers = {"번호", "용어명", "용어설명", "도메인명", "도메인유형", "도메인그룹",
                    "논리 데이터타입", "길이", "소수점", "논리용어명", "물리용어명", "도메인", "상태"};
            IntStream.range(0, headers.length)
                    .forEach(index -> headerRow.createCell(index).setCellValue(headers[index]));

            IntStream.range(0, bulkTermExcelDataDTOList.size())
                    .forEach(index -> {
                        Row dataRow = sheet.createRow(index + 1);
                        BulkTermExcelDataDTO data = bulkTermExcelDataDTOList.get(index);

                        dataRow.createCell(0).setCellValue(++index);
                        dataRow.createCell(1).setCellValue(data.getTempDicLogicalName());
                        dataRow.createCell(2).setCellValue(data.getDicDescription());
                        dataRow.createCell(3).setCellValue(data.getKeyDomainName());
                        dataRow.createCell(4).setCellValue(data.getDomainTypeCode());
                        dataRow.createCell(5).setCellValue(data.getDomainGroupName());
                        dataRow.createCell(6).setCellValue(data.getDataTypeCode());
                        dataRow.createCell(7).setCellValue(data.getDataLength());
                        dataRow.createCell(8).setCellValue(data.getDataScale());
                        dataRow.createCell(9).setCellValue(data.getDicLogicalName());
                        dataRow.createCell(10).setCellValue(data.getDicPhysicalName());
                        dataRow.createCell(11).setCellValue(data.getDomainName());
                        dataRow.createCell(12).setCellValue(String.join("/", data.getReason()));
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
