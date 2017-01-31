package com.accion.recruitment.service.impl;

import com.accion.recruitment.dao.PositionCandidatesServiceDAO;
import com.accion.recruitment.dao.UserServiceDAO;
import com.accion.recruitment.jpa.entities.Candidates;
import com.accion.recruitment.jpa.entities.PositionCandidates;
import com.accion.recruitment.jpa.entities.User;
import com.accion.recruitment.service.PositionCandidatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 31/01/17 00:11 AM#$
 */

@Service("positionCandidateService")
@Transactional
public class PositionCandidatesServiceImpl implements PositionCandidatesService{


    @Autowired
    @Qualifier(value = "positionCandidatesServiceDAOImpl")
    private PositionCandidatesServiceDAO positionCandidatesServiceDAO;

    @Override
    public Boolean savePositionCandidates(final PositionCandidates positionCandidates) throws SQLException{
        Boolean bolValue=this.positionCandidatesServiceDAO.savePositionCandidates(positionCandidates);
        return bolValue;
    }

    @Override
    public List<Candidates> findPositionCandidatesByRequirementId(final int requirementId )throws SQLException {
        return  this.positionCandidatesServiceDAO.findPositionCandidatesByRequirementId(requirementId);
    }

    @Override
    public PositionCandidates findPositionCandidatesByRequirementIdAndCandidateId(final int requirementId,final int candidateId )throws SQLException {
        return  this.positionCandidatesServiceDAO.findPositionCandidatesByRequirementIdAndCandidateId(requirementId,candidateId);
    }

}
