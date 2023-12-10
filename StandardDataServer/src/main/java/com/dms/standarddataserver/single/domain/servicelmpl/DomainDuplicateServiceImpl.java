package com.dms.standarddataserver.single.domain.servicelmpl;

import com.dms.standarddataserver.single.domain.dto.DomainDTO;
import com.dms.standarddataserver.single.domain.dto.DomainGroupDTO;
import com.dms.standarddataserver.single.domain.mapper.DomainMapper;
import com.dms.standarddataserver.single.domain.service.DomainDuplicateService;
import com.dms.standarddataserver.single.domain.service.DomainInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomainDuplicateServiceImpl implements DomainDuplicateService {
    private final DomainMapper domainMapper;

    @Autowired
    public DomainDuplicateServiceImpl(DomainMapper domainMapper)
    {
        this.domainMapper = domainMapper;
    }

    @Override
    public boolean isDuplicateDomain(DomainDTO domainDTO) {
        return domainMapper.isDuplicateDomain(domainDTO);
    }

    @Override
    public boolean isDuplicateDomainGroup(DomainGroupDTO domainGroupDTO) {
        return domainMapper.isDuplicateDomainGroup(domainGroupDTO);
    }


}


