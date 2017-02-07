package com.accion.recruitment.jpa.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "candidate_video_question_response")
public class CandidateVideoQuestionResponse extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id" ,insertable = true, nullable = false, unique = true, updatable = true)
    protected Integer id;

    private  Integer candidateId;

    private  Integer positionId;

    private  Integer questionId;

    private  String  questionName;

    private  String videoLink;

    private  String audioLink;

    private  String status;

    private  String evaluatedBy;

    private  Date evaluatedDate;

    private  Double score;

    @Column(length = 4000)
    private String comment;

    private String clientComment;

    private Double clientScore;

    private String fileName;


    public String getClientComment() {
        return clientComment;
    }

    public void setClientComment(String clientComment) {
        this.clientComment = clientComment;
    }

    public Double getClientScore() {
        return clientScore;
    }

    public void setClientScore(Double clientScore) {
        this.clientScore = clientScore;
    }

    @Transient
    private  String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getAudioLink() {
        return audioLink;
    }

    public void setAudioLink(String audioLink) {
        this.audioLink = audioLink;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEvaluatedBy() {
        return evaluatedBy;
    }

    public void setEvaluatedBy(String evaluatedBy) {
        this.evaluatedBy = evaluatedBy;
    }

    public Date getEvaluatedDate() {
        return evaluatedDate;
    }

    public void setEvaluatedDate(Date evaluatedDate) {
        this.evaluatedDate = evaluatedDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
