package com.dms.userserver.member.service;


import com.dms.userserver.member.dto.MemberDTO;

public interface MemberLoginService {
    public MemberDTO authenticateMember(MemberDTO memberDTO);
}
