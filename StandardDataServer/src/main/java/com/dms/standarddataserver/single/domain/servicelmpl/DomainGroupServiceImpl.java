package com.dms.standarddataserver.single.domain.servicelmpl;

import com.dms.standarddataserver.single.domain.dto.DomainGroupDTO;
import com.dms.standarddataserver.single.domain.mapper.DomainMapper;
import com.dms.standarddataserver.single.domain.service.DomainGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DomainGroupServiceImpl implements DomainGroupService {
    private final DomainMapper domainMapper;

    @Autowired
    public DomainGroupServiceImpl(DomainMapper domainMapper){
        this.domainMapper = domainMapper;
    }

    @Override
    public List<DomainGroupDTO> getDomainGroup() {
        return domainMapper.getDomainGroup();
    }
}


