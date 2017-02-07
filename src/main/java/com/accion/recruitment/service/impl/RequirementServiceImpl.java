package com.accion.recruitment.service.impl;

import com.accion.recruitment.dao.ClientServiceDAO;
import com.accion.recruitment.dao.RequirementServiceDAO;
import com.accion.recruitment.jpa.entities.*;
import com.accion.recruitment.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by MoinGodil on 1/11/17.
 */

@Service("requirementService")
@Transactional
public class RequirementServiceImpl implements RequirementService {


    @Autowired
    @Qualifier(value = "requirementServiceDAOImpl")
    private RequirementServiceDAO requirementServiceDAO;

    @Override
    public Boolean saveRequirements(final Positions requirement) throws SQLException{
        Boolean bolValue=this.requirementServiceDAO.saveRequirements(requirement);
        return bolValue;
    }

    @Override
    public List<Positions> findAllRequirement() throws SQLException{
        List<Positions> requirementList=this.requirementServiceDAO.findAllRequirement();
        return  requirementList;
    }

    @Override
    public Positions findRequirementById(final int positionId )throws SQLException{
        return (Positions) this.requirementServiceDAO.findRequirementById(positionId);
    }

    @Override
    public Boolean deleteRecordByQuery(final String query )throws SQLException{
        return  this.requirementServiceDAO.deleteRecordByQuery(query);
    }


    @Override
    public List<VideoQuestion> findAllRequirementVideoQuestions(Integer requirementId){
        String query="SELECT * \n" +
                "FROM default.position_video_question \n" +
                "inner join default.video_question \n" +
                "on position_video_question.video_question_id=video_question.id \n" +
                "where position_id ='"+requirementId+"' order by position_video_question.video_question_id ASC";

        return  this.requirementServiceDAO.findAllRequirementVideoQuestions(query);

    }

    @Override
    public List<PositionCandidates> findAllRequirementCandidateByRequirementId(Integer requirementId){

           return  this.requirementServiceDAO.findAllRequirementCandidateByRequirementId(requirementId);

    }


}
