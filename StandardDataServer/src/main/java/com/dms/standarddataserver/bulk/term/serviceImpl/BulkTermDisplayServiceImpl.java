package com.dms.standarddataserver.bulk.term.serviceImpl;

import com.dms.standarddataserver.bulk.common.utils.DomainMethodUtil;
import com.dms.standarddataserver.bulk.domain.dto.DomainExcelDataDTO;
import com.dms.standarddataserver.bulk.domain.enums.DomainValidationMessage;
import com.dms.standarddataserver.bulk.domain.mapper.BulkDomainMapper;
import com.dms.standarddataserver.bulk.term.dto.BulkTermExcelDataDTO;
import com.dms.standarddataserver.bulk.term.enums.TermValidationMessage;
import com.dms.standarddataserver.bulk.term.mapper.BulkTermMapper;
import com.dms.standarddataserver.bulk.term.service.BulkTermDisplayService;
import com.dms.standarddataserver.bulk.word.dto.BulkWordDataDTO;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BulkTermDisplayServiceImpl implements BulkTermDisplayService {

    private final BulkDomainMapper bulkDomainMapper;
    private final BulkTermMapper bulkTermMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;

    public BulkTermDisplayServiceImpl(BulkDomainMapper bulkDomainMapper, BulkTermMapper bulkTermMapper, StandardAreaSelectOneService standardAreaSelectOneService) {
        this.bulkDomainMapper = bulkDomainMapper;
        this.bulkTermMapper = bulkTermMapper;
        this.standardAreaSelectOneService = standardAreaSelectOneService;
    }

    @Override
    public List<BulkTermExcelDataDTO> validateBulkTerm(List<BulkTermExcelDataDTO> bulkTermExcelDataDTOList) {
        List<BulkTermExcelDataDTO> bulkTermExcelDataDTOs = new ArrayList<>();
        String stdAreaId = standardAreaSelectOneService.selectOne(bulkTermExcelDataDTOList.get(0).getStdAreaId()).getStdAreaId();
        return bulkTermExcelDataDTOList.stream()
                .map(dto -> {
                    if (isNotRequiredField(dto)) {
                        dto.updateReason(TermValidationMessage.REQUIRED_FIELD_MISSING.getMessage());
                    } else {
                        if (StringUtils.hasText(dto.getTempDicLogicalName()) && isDuplicatedInBulkTermExcel(bulkTermExcelDataDTOs, dto.getTempDicLogicalName())) {
                            dto.updateReason(TermValidationMessage.TERM_NAME_DUPLICATE_IN_EXCEL.getMessage());
                        }

                        if (DomainMethodUtil.isNotMatchDomainTypeCode(dto.getDomainTypeCode())) {
                            dto.updateReason(DomainValidationMessage.DOMAIN_TYPE_MISMATCH.getMessage());
                        } else {
                            dto.setDomainTypeCode(DomainMethodUtil.getDomainType(dto.getDomainTypeCode()));
                        }

                        if (DomainMethodUtil.isNotMatchDataTypeCode(dto.getDataTypeCode())) {
                            dto.updateReason(DomainValidationMessage.DATA_TYPE_MISMATCH.getMessage());
                        } else {
                            dto.setDataTypeCode(DomainMethodUtil.getDataType(dto.getDataTypeCode()));
                        }

                        if (StringUtils.hasText(dto.getDomainGroupName())) {
                            String domainGroupId = getRegisteredDomainGroupId(dto, stdAreaId);
                            if (StringUtils.hasText(domainGroupId)) {
                                dto.setDomainGroupId(domainGroupId);
                            } else {
                                dto.updateReason(DomainValidationMessage.NOT_REGISTERED_IN_DOMAIN_GROUP.getMessage());
                            }
                        }

                        if (dto.getReason().stream().allMatch(String::isEmpty)) {
                            String keyDomainName = dto.getKeyDomainName();
                            String dataTypeCode = dto.getDataTypeCode();
                            int dataLength = dto.getDataLength();
                            int dataScale = dto.getDataScale();
                            String domainName = DomainMethodUtil.createDomainName(keyDomainName, dataTypeCode, dataLength, dataScale);

                            if (StringUtils.hasText(dto.getDomainGroupId())) {
                                if (bulkDomainMapper.isDuplicatedByDomainGroupId(domainName, dto.getDomainGroupId(), stdAreaId) == 0) {
                                    dto.updateReason(TermValidationMessage.NOT_REGISTERED_DOMAIN_IN_DOMAIN_GROUP.getMessage());
                                }
                            } else {
                                if (bulkDomainMapper.isDuplicated(domainName, stdAreaId) == 0) {
                                    dto.updateReason(TermValidationMessage.NOT_REGISTERED_DOMAIN.getMessage());
                                }
                            }

                            if (dto.getReason().stream().allMatch(String::isEmpty)) {
                                dto.updateDomainName(domainName);
                            }
                        }

                        if (dto.getReason().stream().allMatch(String::isEmpty)) {
                            if (isDuplicatedInLogicalName(dto, stdAreaId)) {
                                dto.updateReason(TermValidationMessage.TERM_NAME_DUPLICATE_IN_DB.getMessage());
                            } else {
                                this.checkWordsOfTerm(dto, stdAreaId);
                                if (dto.getReason().stream().allMatch(String::isEmpty)) {
                                    createTermLogicalName(dto);
                                    createTermPhysicalName(dto, stdAreaId);
                                    dto.updateReason(TermValidationMessage.VALIDATION_PASS.getMessage());
                                }
                            }
                        }
                    }
                    bulkTermExcelDataDTOs.add(dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private boolean isNotRequiredField(BulkTermExcelDataDTO dto) {
        return !StringUtils.hasText(dto.getTempDicLogicalName()) || !StringUtils.hasText(dto.getKeyDomainName()) || !StringUtils.hasText(dto.getDomainTypeCode()) || !StringUtils.hasText(dto.getDataTypeCode());
    }

    private boolean isDuplicatedInBulkTermExcel(List<BulkTermExcelDataDTO> bulkTermExcelDataDTOs, String tempDicLogicalName) {
        if (bulkTermExcelDataDTOs.isEmpty()) {
            return false;
        }
        return bulkTermExcelDataDTOs.stream().anyMatch(prevDto -> prevDto.getTempDicLogicalName().equals(tempDicLogicalName));
    }

    private String getRegisteredDomainGroupId(BulkTermExcelDataDTO dto, String stdAreaId) {
        return bulkDomainMapper.getRegisteredDomainGroupId(dto.getDomainGroupName(), stdAreaId);
    }

    private boolean isDuplicatedInLogicalName(BulkTermExcelDataDTO dto, String stdAreaId) {
        String dicLogicalName = replaceUnderscoresWithSpaces(dto);
        return bulkTermMapper.isDuplicatedInLogicalName(dicLogicalName, stdAreaId) != 0;
    }

    private void checkWordsOfTerm(BulkTermExcelDataDTO dto, String stdAreaID) {
        List<String> words = splitTermByUnderscores(dto);

        String lastWordOfTerm = words.get(words.size() - 1);
        BulkWordDataDTO bulkWordDataDTOOfLastWord = checkLastWordOfTerm(lastWordOfTerm,stdAreaID);
        if (bulkWordDataDTOOfLastWord == null) {
            dto.updateReason("[" + lastWordOfTerm + "]" + TermValidationMessage.NOT_REGISTERED_WORD.getMessage());
        } else {
            if ("Y".equals(bulkWordDataDTOOfLastWord.getCheckedStandard())) {
                if ("N".equals(bulkWordDataDTOOfLastWord.getAttributeSuffix())) {
                    dto.updateReason("[" + lastWordOfTerm + "]" + TermValidationMessage.NOT_ATTRIBUTE_SUFFIX.getMessage());
                }
            } else {
                dto.updateReason("[" + lastWordOfTerm + "]" + TermValidationMessage.NOT_STANDARD_WORD.getMessage());
            }
        }

        List<String> wordsWithoutLast = new ArrayList<>(words.subList(0, words.size() - 1));
        wordsWithoutLast.stream()
                .forEach(word -> {
                    String checkedStandard = checkRegisteredWord(word, stdAreaID);
                    if (!StringUtils.hasText(checkedStandard)) {
                        dto.updateReason("[" + word + "]" + TermValidationMessage.NOT_REGISTERED_WORD.getMessage());
                    } else {
                        if ("N".equals(checkedStandard)) {
                            dto.updateReason("[" + word + "]" + TermValidationMessage.NOT_STANDARD_WORD.getMessage());
                        }
                    }
                });
    }

    private List<String> splitTermByUnderscores(BulkTermExcelDataDTO dto) {
        return List.of(dto.getTempDicLogicalName().trim().split("_"));
    }

    private String checkRegisteredWord(String word, String stdAreaId) {
        return bulkTermMapper.checkRegisteredWord(word, stdAreaId);
    }

    private BulkWordDataDTO checkLastWordOfTerm(String word, String stdAreaId) {
        return bulkTermMapper.checkLastWordOfTerm(word, stdAreaId);
    }

    private String replaceUnderscoresWithSpaces(BulkTermExcelDataDTO dto) {
        return dto.getTempDicLogicalName().trim().replace("_", " ");
    }

    private void createTermLogicalName(BulkTermExcelDataDTO dto) {
        String dicLogicalName = replaceUnderscoresWithSpaces(dto);
        dto.setDicLogicalName(dicLogicalName);
    }

    private void createTermPhysicalName(BulkTermExcelDataDTO dto, String stdAreaId) {
        List<String> words = splitTermByUnderscores(dto);
        String dicPhysicalName = words.stream()
                .map(word -> {
                    String physicalNameOfWord = bulkTermMapper.findPhysicalNameOfWord(word, stdAreaId);
                    return physicalNameOfWord;
                })
                .collect(Collectors.joining(" "));
        dto.setDicPhysicalName(dicPhysicalName);
    }

}
