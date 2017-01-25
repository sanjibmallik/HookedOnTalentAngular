package com.accion.recruitment.jpa.entities;

/**
 * Created by MoinGodil on 1/25/17.
 */
public class QuestionBaseClass extends BaseEntity{
    String questionType;
    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
}
