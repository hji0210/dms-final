package com.dms.standarddataserver.standardArea.serviceImpl;

import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.mapper.StandardAreaDeleteMapper;
import com.dms.standarddataserver.standardArea.service.StandardAreaCheckDuplicateService;
import com.dms.standarddataserver.standardArea.service.StandardAreaDeleteService;
import org.springframework.stereotype.Service;

@Service
public class StandardAreaDeleteServiceImpl implements StandardAreaDeleteService {
    private final StandardAreaDeleteMapper standardAreaDeleteMapper;
    private final StandardAreaCheckDuplicateService standardAreaCheckDuplicateService;

    public StandardAreaDeleteServiceImpl(StandardAreaDeleteMapper standardAreaDeleteMapper, StandardAreaCheckDuplicateService standardAreaCheckDuplicateService) {
        this.standardAreaDeleteMapper = standardAreaDeleteMapper;
        this.standardAreaCheckDuplicateService = standardAreaCheckDuplicateService;
    }

    @Override
    public boolean delete(StandardAreaDTO standardAreaDTO) {
        standardAreaDeleteMapper.delete(standardAreaDTO);
        return !standardAreaCheckDuplicateService.checkIfDuplicate(standardAreaDTO.getStdAreaNm());
    }
}
