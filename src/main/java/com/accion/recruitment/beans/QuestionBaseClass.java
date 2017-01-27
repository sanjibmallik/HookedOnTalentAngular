package com.accion.recruitment.beans;

import com.accion.recruitment.jpa.entities.BaseEntity;
import com.accion.recruitment.jpa.entities.GeneralQuestion;
import com.accion.recruitment.jpa.entities.TechnicalQuestion;
import com.accion.recruitment.jpa.entities.VideoQuestion;

/**
 * Created by MoinGodil on 1/25/17.
 */
public class QuestionBaseClass {
    private String questionType;
    private Integer positionId;

    private GeneralQuestion generalQuestion;
    private TechnicalQuestion technicalQuestion;
    private VideoQuestion videoQuestion;

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public GeneralQuestion getGeneralQuestion() {
        return generalQuestion;
    }

    public void setGeneralQuestion(GeneralQuestion generalQuestion) {
        this.generalQuestion = generalQuestion;
    }

    public TechnicalQuestion getTechnicalQuestion() {
        return technicalQuestion;
    }

    public void setTechnicalQuestion(TechnicalQuestion technicalQuestion) {
        this.technicalQuestion = technicalQuestion;
    }

    public VideoQuestion getVideoQuestion() {
        return videoQuestion;
    }

    public void setVideoQuestion(VideoQuestion videoQuestion) {
        this.videoQuestion = videoQuestion;
    }
}
