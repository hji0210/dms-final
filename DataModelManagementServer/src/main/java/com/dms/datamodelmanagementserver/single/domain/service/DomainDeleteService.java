package com.dms.datamodelmanagementserver.single.domain.service;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;

public interface DomainDeleteService {


    boolean deleteDomain(DomainDTO domainDTO);
}
