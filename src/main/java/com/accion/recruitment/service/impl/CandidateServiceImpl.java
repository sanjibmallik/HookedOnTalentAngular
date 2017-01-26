package com.accion.recruitment.service.impl;

import com.accion.recruitment.dao.CandidateServiceDAO;
import com.accion.recruitment.dao.RequirementServiceDAO;
import com.accion.recruitment.jpa.entities.Candidates;
import com.accion.recruitment.jpa.entities.Positions;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 25/01/17 00:11 AM#$
 */
@Service("candidateService")
@Transactional
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    @Qualifier(value = "candidateServiceDAOImpl")
    private CandidateServiceDAO candidateServiceDAO;

    @Override
    public Boolean saveCandidates(final Candidates candidates) throws SQLException {
        Boolean bolValue=this.candidateServiceDAO.saveCandidates(candidates);
        return bolValue;
    }

    @Override
    public List<Candidates> findAllCandidates() throws SQLException{
        List<Candidates> userList=this.candidateServiceDAO.findAllCandidates();
        return  userList;
    }

    @Override
    public Candidates findCandidatesByPropertyName(final String propName,final Object propValue)throws SQLException{
        return (Candidates) this.candidateServiceDAO.findCandidatesByPropertyName(propName, propValue);
    }

    @Override
    public Candidates findCandidatesById(final int candidateId) throws SQLException{
        return (Candidates) this.candidateServiceDAO.findCandidatesById(candidateId);
    }

}
