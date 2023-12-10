package com.dms.datamodelmanagementserver.global.session.Controller;

import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.global.session.service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/dms/session")
public class SessionController {
    private final LogDefault logDefault;
    private final SessionService sessionService;

    public SessionController(LogDefault logDefault, SessionService sessionService) {
        this.logDefault = logDefault;
        this.sessionService = sessionService;
    }

    @PostMapping(value = "/get")
    public ResponseEntity<String> getSession() {
        logDefault.logCurrentMethod();
        String selectedStandardArea = sessionService.getSession();
        return ResponseEntity.status(HttpStatus.OK).body(selectedStandardArea);
    }

    @PostMapping(value = "/set")
    public ResponseEntity<String> setSession(@RequestBody String selectedStandardArea) {
        logDefault.logCurrentMethod();
        sessionService.setSession(selectedStandardArea.replaceAll("\"", ""));
        return ResponseEntity.status(HttpStatus.OK).body("세션 저장 완료");
    }
}
