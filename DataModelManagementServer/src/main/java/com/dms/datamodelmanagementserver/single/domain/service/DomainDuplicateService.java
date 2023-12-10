package com.dms.datamodelmanagementserver.single.domain.service;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainGroupDTO;

public interface  DomainDuplicateService {
    public boolean isDuplicateDomainRest(DomainDTO domainDTO);

    public boolean isDuplicateDomainGroupRest(DomainGroupDTO domainGroupDTO);
}
