package com.accion.recruitment.service.impl;

import com.accion.recruitment.dao.CandidatePastHistoryServiceDAO;
import com.accion.recruitment.dao.PositionCandidatesServiceDAO;
import com.accion.recruitment.jpa.entities.CandidatePastHistory;
import com.accion.recruitment.jpa.entities.PositionCandidates;
import com.accion.recruitment.service.CandidatePastHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 31/01/17 00:11 AM#$
 */

@Service("candidatePastHistoryService")
@Transactional
public class CandidatePastHistoryServiceImpl implements CandidatePastHistoryService {

    @Autowired
    @Qualifier(value = "candidatePastHistoryServiceDAOImpl")
    private CandidatePastHistoryServiceDAO candidatePastHistoryServiceDAO;


    @Override
    public Boolean saveCandidatePastHistory(final CandidatePastHistory candidatePastHistory) throws SQLException {
        Boolean bolValue=this.candidatePastHistoryServiceDAO.saveCandidatePastHistory(candidatePastHistory);
        return bolValue;
    }

}
