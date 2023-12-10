package com.dms.userserver.member.serviceImpl;

import com.dms.userserver.member.dto.MemberDTO;
import com.dms.userserver.member.mapper.MemberLoginMapper;
import com.dms.userserver.member.service.MemberLoginService;
import org.springframework.stereotype.Service;

@Service
public class MemberLoginServiceImpl implements MemberLoginService {
    public final MemberLoginMapper memberLoginMapper;

    public MemberLoginServiceImpl(MemberLoginMapper memberLoginMapper) {
        this.memberLoginMapper = memberLoginMapper;
    }

    @Override
    public MemberDTO authenticateMember(MemberDTO memberDTO) {
        return memberLoginMapper.authenticateMember(memberDTO) ;
    }
}
