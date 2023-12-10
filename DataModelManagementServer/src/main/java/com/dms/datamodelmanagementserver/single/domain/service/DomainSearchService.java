package com.dms.datamodelmanagementserver.single.domain.service;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;

import java.util.List;

public interface DomainSearchService {

    List<DomainDTO> searchDomainNameByDomainNameRest(String stdAreaId, String domainName);

}
