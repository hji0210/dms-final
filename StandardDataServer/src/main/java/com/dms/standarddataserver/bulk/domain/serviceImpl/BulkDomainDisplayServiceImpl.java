package com.dms.standarddataserver.bulk.domain.serviceImpl;

import com.dms.standarddataserver.bulk.common.utils.DomainMethodUtil;
import com.dms.standarddataserver.bulk.domain.dto.DomainExcelDataDTO;
import com.dms.standarddataserver.bulk.domain.enums.DomainValidationMessage;
import com.dms.standarddataserver.bulk.domain.mapper.BulkDomainMapper;
import com.dms.standarddataserver.bulk.domain.service.BulkDomainDisplayService;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BulkDomainDisplayServiceImpl implements BulkDomainDisplayService {

    private final BulkDomainMapper bulkDomainMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;

    public BulkDomainDisplayServiceImpl(BulkDomainMapper bulkDomainMapper,
                                        StandardAreaSelectOneService standardAreaSelectOneService) {
        this.bulkDomainMapper = bulkDomainMapper;
        this.standardAreaSelectOneService = standardAreaSelectOneService;
    }

    @Override
    public List<DomainExcelDataDTO> validateBulkDomain(List<DomainExcelDataDTO> domainExcelDataDTOList) {
        List<DomainExcelDataDTO> domainExcelDataDTOs = new ArrayList<>();
        String stdAreaName = domainExcelDataDTOList.get(0).getStdAreaId();
        String stdAreaId = standardAreaSelectOneService.selectOne(stdAreaName).getStdAreaId();

        return domainExcelDataDTOList.stream()
                .map(dto -> {
                    if (isNotRequiredField(dto)) {
                        dto.updateReason(DomainValidationMessage.REQUIRED_FIELD_MISSING.getMessage());
                        return dto;
                    } else {
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

                            if (isDuplicatedInExcel(domainExcelDataDTOs, domainName, dto)) {
                                dto.updateReason(DomainValidationMessage.DOMAIN_NAME_DUPLICATE_IN_EXCEL.getMessage());
                            }

                            if (StringUtils.hasText(dto.getDomainGroupId())) {
                                if (bulkDomainMapper.isDuplicatedByDomainGroupId(domainName, dto.getDomainGroupId(), stdAreaId) > 0) {
                                    dto.updateReason(DomainValidationMessage.DOMAIN_NAME_DUPLICATE_IN_DOMAIN_GROUP.getMessage());
                                }
                            } else {
                                if (bulkDomainMapper.isDuplicated(domainName, stdAreaId) > 0) {
                                    dto.updateReason(DomainValidationMessage.DOMAIN_NAME_DUPLICATE.getMessage());
                                }
                            }

                            if (dto.getReason().stream().allMatch(String::isEmpty)) {
                                dto.updateDomainName(domainName);
                                dto.updateReason(DomainValidationMessage.VALIDATION_PASS.getMessage());
                            }
                            domainExcelDataDTOs.add(dto);
                        }
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private boolean isNotRequiredField(DomainExcelDataDTO dto) {
        return !StringUtils.hasText(dto.getKeyDomainName()) || !StringUtils.hasText(dto.getDomainTypeCode()) || !StringUtils.hasText(dto.getDataTypeCode());
    }

    private boolean isDuplicatedInExcel(List<DomainExcelDataDTO> domainExcelDataDTOs, String domainName, DomainExcelDataDTO dto) {
        if (domainExcelDataDTOs == null) {
            return false;
        }
        List<String> domainGroupIdList = domainExcelDataDTOs.stream().map(it -> it.getDomainGroupId()).filter(Objects::nonNull).distinct().collect(Collectors.toList());
        if (StringUtils.hasText(dto.getDomainGroupId())) {
            if (domainGroupIdList.stream().allMatch(String::isEmpty)) {
                return false;
            }
            return domainGroupIdList.stream().anyMatch(id -> id.equals(dto.getDomainGroupId())) && domainExcelDataDTOs.stream().anyMatch(it -> it.getDomainName().equals(domainName));
        }
        return domainExcelDataDTOs.stream().anyMatch(it -> it.getDomainName().equals(domainName));
    }

    private String getRegisteredDomainGroupId(DomainExcelDataDTO dto, String stdAreaId) {
        return bulkDomainMapper.getRegisteredDomainGroupId(dto.getDomainGroupName(), stdAreaId);
    }
}