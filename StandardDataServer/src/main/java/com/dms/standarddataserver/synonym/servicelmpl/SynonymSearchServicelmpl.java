package com.dms.standarddataserver.synonym.servicelmpl;

import com.dms.standarddataserver.global.LogDefault;
import com.dms.standarddataserver.synonym.dto.SynonymDTO;
import com.dms.standarddataserver.synonym.mapper.SynonymSearchMapper;
import com.dms.standarddataserver.synonym.service.SynonymSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SynonymSearchServicelmpl implements SynonymSearchService {
    private final SynonymSearchMapper synonymSearchMapper;
    private final LogDefault logDefault;


    public SynonymSearchServicelmpl(SynonymSearchMapper synonymSearchMapper, LogDefault logDefault) {
        this.synonymSearchMapper = synonymSearchMapper;
        this.logDefault = logDefault;
    }


    @Override
    public List<SynonymDTO> selectList(SynonymDTO synonymDTO) {

        logDefault.logCurrentMethod();

        List<SynonymDTO> Log = synonymSearchMapper.selectList(synonymDTO);
        for (SynonymDTO inner : Log) {
            log.info(inner.getDicLogNm());
        }
        return synonymSearchMapper.selectList(synonymDTO);
    }

}


