package com.dms.userserver.member.mapper;

import com.dms.userserver.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberLoginMapper {
    public MemberDTO authenticateMember(@Param("memberDTO")MemberDTO memberDTO);
}
