package com.dms.standarddataserver.standardArea.serviceImpl;

import com.dms.standarddataserver.standardArea.mapper.DomainGroupAutoMapper;
import com.dms.standarddataserver.standardArea.service.DomainGroupInsertAutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class DomainGroupAutoServiceImpl implements DomainGroupInsertAutoService {
    private final DomainGroupAutoMapper domainGroupAutoMapper;

    public DomainGroupAutoServiceImpl(DomainGroupAutoMapper domainGroupAutoMapper) {
        this.domainGroupAutoMapper = domainGroupAutoMapper;
    }

    @Override
    public void insertDomainGroup(String standardAreaId) {
        domainGroupAutoMapper.insertDomainGroup(UUID.randomUUID().toString(), standardAreaId);
    }
}
