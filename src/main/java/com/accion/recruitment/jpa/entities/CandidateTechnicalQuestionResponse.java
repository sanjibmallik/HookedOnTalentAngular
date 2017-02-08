package com.accion.recruitment.jpa.entities;

import javax.persistence.*;

@Entity
@Table(name = "candidate_technical_question_response")
public class CandidateTechnicalQuestionResponse extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id" ,insertable = true, nullable = false, unique = true, updatable = true)
    protected Integer id;

    private  Integer candidateId;
    private  Integer positionId;
    private  Integer questionId;
    @Column(length = 3000)
    private  String  questionName;
    @Column(length = 3000)
    private  String candidateResponse;
    @Column(length = 3000)
    private  String answer;
    private  String status;
    private Double score;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getCandidateResponse() {
        return candidateResponse;
    }

    public void setCandidateResponse(String candidateResponse) {
        this.candidateResponse = candidateResponse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
