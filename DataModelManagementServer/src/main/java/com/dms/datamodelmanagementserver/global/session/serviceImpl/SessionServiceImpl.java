package com.dms.datamodelmanagementserver.global.session.serviceImpl;

import com.dms.datamodelmanagementserver.global.session.mapper.SessionMapper;
import com.dms.datamodelmanagementserver.global.session.service.SessionService;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {
    private final SessionMapper sessionMapper;

    public SessionServiceImpl(SessionMapper sessionMapper) {
        this.sessionMapper = sessionMapper;
    }

    @Override
    public void setSession(String selectedStandardArea) {
        sessionMapper.setSession(selectedStandardArea);
    }

    @Override
    public String getSession() {
        return sessionMapper.getSession();
    }
}
