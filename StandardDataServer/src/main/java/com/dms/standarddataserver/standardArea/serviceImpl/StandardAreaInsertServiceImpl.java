package com.dms.standarddataserver.standardArea.serviceImpl;

import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.mapper.StandardAreaInsertMapper;
import com.dms.standarddataserver.standardArea.service.DomainGroupInsertAutoService;
import com.dms.standarddataserver.standardArea.service.StandardAreaInsertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class StandardAreaInsertServiceImpl implements StandardAreaInsertService {
    private final StandardAreaInsertMapper standardAreaInsertMapper;
    private final DomainGroupInsertAutoService domainGroupInsertAutoService;

    public StandardAreaInsertServiceImpl(StandardAreaInsertMapper standardAreaInsertMapper, DomainGroupInsertAutoService domainGroupInsertAutoService) {
        this.standardAreaInsertMapper = standardAreaInsertMapper;
        this.domainGroupInsertAutoService = domainGroupInsertAutoService;
    }

    @Override
    public void insert(StandardAreaDTO standardAreaDTO) {
        standardAreaDTO.setStdAreaId(UUID.randomUUID().toString());
        domainGroupInsertAutoService.insertDomainGroup(standardAreaDTO.getStdAreaId());
        standardAreaInsertMapper.insert(standardAreaDTO);
    }
}
