package com.accion.recruitment.service;

import com.accion.recruitment.jpa.entities.*;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 23/01/17 00:11 AM#$
 */
public interface QuestionService  {

    /**
     * saveQuestion() provide the specification for persisting
     * the question object in the database.
     *
     * @param question accept the instance of the GeneralQuestion/TechnicalQuestion/VideoQuestion class
     *             containing data.
     * @return status of the persisting operation.
     */
    public Boolean saveQuestion(final Object question)throws SQLException;

    /**
     * saveDomain() provide the specification for persisting
     * the Domain object in the database.
     *
     * @param domain accept the instance of the Domain class
     *             containing data.
     * @return status of the persisting operation.
     */
    public Boolean saveDomain(final Domain domain)throws SQLException;

    /**
     * findRequirementGenericGeneralQuestion() get the list requirement generic question
     *
     * @param addToPositionStatus accept the java.lang.String type
     *                  which is having Database Query
     *
     * @return returns the list of generic general questions
     */
    public List<GeneralQuestion> findRequirementGenericGeneralQuestion(String addToPositionStatus) throws SQLException;


    /**
     * findQuestionById() provide the specification for retrieval of the
     *  object from the database based on the questionId.
     *
     * @param questionId accept the java.lang.int type of userId
     *                  which is having questionId ID value
     *
     * @return instance of Object having persisting state otherwise
     *         it return null.
     */
    public Object findQuestionById(final int questionId) throws SQLException;










     /*  *//**
     * saveTechnicalQuestion() provide the specification for persisting
     * the TechnicalQuestion object in the database.
     *
     * @param technicalQuestion accept the instance of the TechnicalQuestion class
     *             containing data.
     * @return status of the persisting operation.
     *//*
    public Boolean saveTechnicalQuestion(final TechnicalQuestion technicalQuestion)throws SQLException;

    *//**
     * saveVideoQuestion() provide the specification for persisting
     * the VideoQuestion object in the database.
     *
     * @param videoQuestion accept the instance of the VideoQuestion class
     *             containing data.
     * @return status of the persisting operation.
     *//*
    public Boolean saveVideoQuestion(final VideoQuestion videoQuestion)throws SQLException;
*/

}
