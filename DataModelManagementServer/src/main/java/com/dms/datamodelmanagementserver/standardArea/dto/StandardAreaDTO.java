package com.dms.datamodelmanagementserver.standardArea.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardAreaDTO {
    private String stdAreaId;
    private String stdAreaNm;
    private String stdAreaDesc;
    private String avalStDt;
    private String avalEndDt;
    private String message;
}
