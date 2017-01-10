package com.accion.recruitment.jpa.entities;


import javax.persistence.*;


/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 09/01/17 00:11 AM#$
 */



@Entity
@Table(name = "candidate_past_history")

public class CandidatePastHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id" ,insertable = true, nullable = false, unique = true, updatable = true)
    protected Integer id;


    private Integer positionId;
    private String positionName;
    private String clientName;
    private String primarySkill;
    @Transient
    private String addedBy;
    @Transient
    private String evaluatedBy;
    private Double score;

    @Transient
    private String average;

    @Transient
    private String finalVerdict;

    public String getFinalVerdict() {
        return finalVerdict;
    }

    public void setFinalVerdict(String finalVerdict) {
        this.finalVerdict = finalVerdict;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getEvaluatedBy() {
        return evaluatedBy;
    }

    public void setEvaluatedBy(String evaluatedBy) {
        this.evaluatedBy = evaluatedBy;
    }

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional=false)
    @JoinColumn(name = "candidateId")
    private Candidates candidatePastHistory;

    public Candidates getCandidatePastHistory() {
        return candidatePastHistory;
    }

    public void setCandidatePastHistory(Candidates candidatePastHistory) {
        this.candidatePastHistory = candidatePastHistory;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }


    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPrimarySkill() {
        return primarySkill;
    }

    public void setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }
}
