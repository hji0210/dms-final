package com.dms.userserver.member.controller;

import com.dms.userserver.member.dto.MemberDTO;
import com.dms.userserver.member.service.MemberLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class MemberLoginController {
    private final MemberLoginService memberLoginService;

    @Autowired
    public MemberLoginController(MemberLoginService memberLoginService) {
        this.memberLoginService = memberLoginService;
    }

    @PostMapping("/login")
    private ResponseEntity<?> authenticateMember(@RequestBody MemberDTO memberDTO) {
        log.info(memberDTO.getId()+memberDTO.getName()+memberDTO.getPassword());

        MemberDTO resultMemberDTO = memberLoginService.authenticateMember(memberDTO);
        if (resultMemberDTO == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(resultMemberDTO);
        }
    }
}
