package com.accion.recruitment.service.impl;

import com.accion.recruitment.dao.QuestionServiceDAO;
import com.accion.recruitment.dao.RequirementServiceDAO;
import com.accion.recruitment.jpa.entities.GeneralQuestion;
import com.accion.recruitment.jpa.entities.Positions;
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
    public Boolean saveGeneralQuestion(final GeneralQuestion generalQuestion) throws SQLException{
        Boolean bolValue=this.questionServiceDAO.saveGeneralQuestion(generalQuestion);
        return bolValue;
    }


    public List<GeneralQuestion> findRequirementGenericGeneralQuestion(String addToPositionStatus) throws SQLException{
        return this.questionServiceDAO.findRequirementGenericGeneralQuestion(addToPositionStatus);
    }
}
