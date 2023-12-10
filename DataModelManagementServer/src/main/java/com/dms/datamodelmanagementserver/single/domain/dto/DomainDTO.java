package com.dms.datamodelmanagementserver.single.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DomainDTO {
    private UUID domId;
    private String selectStandardArea;
    private String keyDomName;
    private String domName;
    private String domDescription;
    private String domGroupId;
    private String domTypeCode;
    private String dataTypeCode;
    private String dataLen;
    private String dataScale;
    private Integer dataMin;
    private Integer dataMax;
    private String avalStartDate;
    private String avalEndDate;
}





