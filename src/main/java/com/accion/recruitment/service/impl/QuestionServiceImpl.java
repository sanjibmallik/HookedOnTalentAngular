package com.accion.recruitment.service.impl;

import com.accion.recruitment.dao.QuestionServiceDAO;
import com.accion.recruitment.dao.RequirementServiceDAO;
import com.accion.recruitment.jpa.entities.*;
import com.accion.recruitment.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by MoinGodil on 1/20/17.
 */

@Service("questionService")
@Transactional
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    @Qualifier(value = "questionServiceDAOImpl")
    private QuestionServiceDAO questionServiceDAO;

    @Override
    public Boolean saveQuestion(final Object question) throws SQLException{
        Boolean bolValue=this.questionServiceDAO.saveQuestion(question);
        return bolValue;
    }

    @Override
    public Boolean saveDomain(final Domain domain) throws SQLException{
        Boolean bolValue=this.questionServiceDAO.saveDomain(domain);
        return bolValue;
    }


    public List<GeneralQuestion> findRequirementGenericGeneralQuestion(String addToPositionStatus) throws SQLException{
        return this.questionServiceDAO.findRequirementGenericGeneralQuestion(addToPositionStatus);
    }

    @Override
    public Object findQuestionById(final int questionId) throws SQLException{
        return (Candidates) this.questionServiceDAO.findQuestionById(questionId);
    }
}
