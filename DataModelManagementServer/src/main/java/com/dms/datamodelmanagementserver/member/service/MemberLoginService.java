package com.dms.datamodelmanagementserver.member.service;

import com.dms.datamodelmanagementserver.member.dto.MemberDTO;

public interface MemberLoginService {
    public MemberDTO authenticateMember(MemberDTO memberDTO);

}
