package com.dms.datamodelmanagementserver.single.domain.service;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;

public interface  DomainInfoService {


    public DomainDTO getDomainInfoRest(String domId);
}


