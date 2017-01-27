package com.accion.recruitment.dao;

import com.accion.recruitment.jpa.entities.Domain;
import com.accion.recruitment.jpa.entities.GeneralQuestion;
import com.accion.recruitment.jpa.entities.TechnicalQuestion;
import com.accion.recruitment.jpa.entities.VideoQuestion;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 23/01/17 00:11 AM#$
 */
public interface QuestionServiceDAO<E> {

    /**
     * saveGeneralQuestion() provide the specification for persisting
     * the question object in the database.
     *
     * @param question instance of the GeneralQuestion/TechnicalQuestion/VideoQuestion class
     *             containing data.
     * @return status of the persisting operation.
     */
    public Boolean saveQuestion(final Object question);


    /**
     * saveDomain() provide the specification for persisting
     * the Domain object in the database.
     *
     * @param domain accept the instance of the Domain class
     *             containing data.
     * @return status of the persisting operation.
     */
    public Boolean saveDomain(final Domain domain);


    /**
     * findRequirementGenericGeneralQuestion() get the requirement generic question
     *
     * @param addToPositionStatus accept the java.lang.String type
     *                  which is having Database Query
     *
     * @return returns the list of generic general questions
     */
    public List<GeneralQuestion> findRequirementGenericGeneralQuestion(String addToPositionStatus);

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



}
