package com.dms.standarddataserver.bulk.word.serviceImpl;

import com.dms.standarddataserver.bulk.common.enums.RegularExpressionEnum;
import com.dms.standarddataserver.bulk.word.dto.WordExcelDataDTO;
import com.dms.standarddataserver.bulk.word.enums.WordValidationMessage;
import com.dms.standarddataserver.bulk.word.mapper.BulkWordMapper;
import com.dms.standarddataserver.bulk.word.service.BulkWordDisplayService;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class BulkWordDisplayServiceImpl implements BulkWordDisplayService {

    private final BulkWordMapper bulkWordMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;

    public BulkWordDisplayServiceImpl(BulkWordMapper bulkWordMapper, StandardAreaSelectOneService standardAreaSelectOneService) {
        this.bulkWordMapper = bulkWordMapper;
        this.standardAreaSelectOneService = standardAreaSelectOneService;
    }

    @Override
    public List<WordExcelDataDTO> validateBulkWord(List<WordExcelDataDTO> wordExcelDataDTOList) {
        List<WordExcelDataDTO> wordExcelDataDTOs = new ArrayList<>();
        String stdAreaId = standardAreaSelectOneService.selectOne(wordExcelDataDTOList.get(0).getStdAreaId()).getStdAreaId();
        return wordExcelDataDTOList.stream()
                .map(dto -> {
                    if (isNotRequiredField(dto)) {
                        dto.updateReason(WordValidationMessage.REQUIRED_FIELD_MISSING.getMessage());
                        return dto;
                    } else {
                        if (isSpecialCharacters(dto.getDicLogicalName())) {
                            dto.updateReason(WordValidationMessage.NO_SPECIAL_CHARACTERS.getMessage());
                        }

                        if (isNotEnglish(dto.getDicPhysicalName())) {
                            dto.updateReason(WordValidationMessage.NOT_ONLY_ENGLISH_NO_SPECIAL_CHARACTERS.getMessage());
                        }

                        if (isNotEnglishForFullName(dto.getDicPhysicalFullName())) {
                            dto.updateReason(WordValidationMessage.NOT_ONLY_ENGLISH.getMessage());
                        }

                        if (isDuplicatedInLogicalNameOfExcel(wordExcelDataDTOs, dto.getDicLogicalName())) {
                            dto.updateReason(WordValidationMessage.LOGICAL_NAME_DUPLICATE_IN_EXCEL.getMessage());
                        }

                        if (isDuplicatedInLogicalName(dto.getDicLogicalName(), stdAreaId)) {
                            dto.updateReason(WordValidationMessage.LOGICAL_NAME_DUPLICATE.getMessage());
                        }

                        if (isDuplicatedInPhysicalNameOfExcel(wordExcelDataDTOs, dto.getDicPhysicalName())) {
                            dto.updateReason(WordValidationMessage.PHYSICAL_NAME_DUPLICATE_IN_EXCEL.getMessage());
                        }

                        if (isDuplicatedInPhysicalName(dto.getDicPhysicalName(), stdAreaId)) {
                            dto.updateReason(WordValidationMessage.PHYSICAL_NAME_DUPLICATE.getMessage());
                        }

                        if (dto.getSynonym() != null) {
                            validateDuplicatedInSynonym(dto, stdAreaId);
                        }

                        if (dto.getReason() == null) {
                            dto.updateReason(WordValidationMessage.VALIDATION_PASS.getMessage());
                        }
                        wordExcelDataDTOs.add(dto);
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private boolean isNotRequiredField(WordExcelDataDTO dto) {
        return !StringUtils.hasText(dto.getDicLogicalName()) || !StringUtils.hasText(dto.getDicPhysicalName()) || !StringUtils.hasText(dto.getDicPhysicalFullName());
    }

    private boolean isSpecialCharacters(String logicalName) {
        return !Pattern.matches(RegularExpressionEnum.ENGLISH_AND_KOREAN_NO_SPECIAL_CHARACTERS.getRegex(), logicalName);
    }

    private boolean isNotEnglish(String physicalName) {
        return !Pattern.matches(RegularExpressionEnum.ENGLISH_ONLY.getRegex(), physicalName);
    }

    private boolean isNotEnglishForFullName(String physicalFullName) {
        return !Pattern.matches(RegularExpressionEnum.ENGLISH_WITH_SPECIAL_CHARACTERS.getRegex(), physicalFullName);
    }

    private boolean isDuplicatedInLogicalNameOfExcel(List<WordExcelDataDTO> wordExcelDataDTOs, String logicalName) {
        if (wordExcelDataDTOs.isEmpty()) {
            return false;
        }
        return wordExcelDataDTOs.stream().anyMatch(dto -> dto.getDicLogicalName().equals(logicalName));
    }

    private boolean isDuplicatedInLogicalName(String logicalName, String stdAreaId) {
        return bulkWordMapper.isDuplicatedInLogicalName(logicalName, stdAreaId) > 0;
    }

    private boolean isDuplicatedInPhysicalNameOfExcel(List<WordExcelDataDTO> wordExcelDataDTOs, String physicalName) {
        if (wordExcelDataDTOs.isEmpty()) {
            return false;
        }
        return wordExcelDataDTOs.stream().anyMatch(dto -> dto.getDicPhysicalName().equals(physicalName));
    }

    private boolean isDuplicatedInPhysicalName(String physicalName, String stdAreaId) {
        return bulkWordMapper.isDuplicatedInPhysicalName(physicalName, stdAreaId) > 0;
    }

    private void validateDuplicatedInSynonym(WordExcelDataDTO dto, String stdAreaId) {
        List<String> synonyms = dto.getSynonym();
        synonyms.stream().forEach(synonym -> {
            String standardYn = bulkWordMapper.validateDuplicatedInSynonym(synonym, stdAreaId);
            if ("Y".equals(standardYn)) {
                dto.updateReason("[" + synonym + "]" + WordValidationMessage.STANDARD_LOGICAL_NAME_DUPLICATE_IN_SYNONYM.getMessage());
            } else if ("N".equals(standardYn)) {
                dto.updateReason("[" + synonym + "]" + WordValidationMessage.NONSTANDARD_LOGICAL_NAME_DUPLICATE_IN_SYNONYM.getMessage());
            }
        });
    }
}
