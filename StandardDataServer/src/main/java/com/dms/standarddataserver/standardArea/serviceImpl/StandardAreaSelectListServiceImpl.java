package com.dms.standarddataserver.standardArea.serviceImpl;

import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.mapper.StandardAreaSelectListMapper;
import com.dms.standarddataserver.standardArea.service.StandardAreaInsertService;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandardAreaSelectListServiceImpl implements StandardAreaSelectListService {
    private final StandardAreaSelectListMapper standardAreaSelectListMapper;

    public StandardAreaSelectListServiceImpl(StandardAreaSelectListMapper standardAreaSelectListMapper) {
        this.standardAreaSelectListMapper = standardAreaSelectListMapper;
    }

    @Override
    public List<StandardAreaDTO> selectList() {
        return standardAreaSelectListMapper.selectList();
    }
}
