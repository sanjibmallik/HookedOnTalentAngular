package com.accion.recruitment.service;

import com.accion.recruitment.jpa.entities.GeneralQuestion;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 23/01/17 00:11 AM#$
 */
public interface QuestionService  {

    /**
     * saveGeneralQuestion() provide the specification for persisting
     * the GeneralQuestion object in the database.
     *
     * @param generalQuestion accept the instance of the GeneralQuestion class
     *             containing data.
     * @return status of the persisting operation.
     */
    public Boolean saveGeneralQuestion(final GeneralQuestion generalQuestion)throws SQLException;



    /**
     * findRequirementGenericGeneralQuestion() get the list requirement generic question
     *
     * @param addToPositionStatus accept the java.lang.String type
     *                  which is having Database Query
     *
     * @return returns the list of generic general questions
     */
    public List<GeneralQuestion> findRequirementGenericGeneralQuestion(String addToPositionStatus) throws SQLException;
}
