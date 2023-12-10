package com.dms.standarddataserver.standardArea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DomainGroupAutoMapper {
    void insertDomainGroup(@Param("domainGroupId") String domainId,@Param("standardAreaId") String standardAreaId);
}
