package com.accion.recruitment.jpa.entities;




import javax.persistence.*;
import java.util.Date;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 09/01/17 00:11 AM#$
 */

@Entity
@Table(name = "position_candidates")

public class PositionCandidates{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="position_candidates_id" ,insertable = false, nullable = false, unique = true, updatable = true)
    protected Integer id;

    private String linkValidity;

    private String candidateLink;

    private String status;

    private Double score;

    private String screenedStatus;

    private String candidateEnableDisable;


    @Column(length = 4000)
    private String comment;

    private String evalutedByTS;

    private String isShortListed;

    private Integer linkCount;

    private String addedBy;

    private String submitToClient;

    public String getSubmitToClient() {
        return submitToClient;
    }

    public void setSubmitToClient(String submitToClient) {
        this.submitToClient = submitToClient;
    }

    private Integer autoReminderCount;

    public Integer getAutoReminderCount() {
        return autoReminderCount;
    }

    public void setAutoReminderCount(Integer autoReminderCount) {
        this.autoReminderCount = autoReminderCount;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_DATE", nullable = true, length = 7)
    private Date updatedDate = new Date();


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", nullable = true, length = 7)
    private Date createdDate = new Date();

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public Integer getLinkCount() {
        return linkCount;
    }

    public void setLinkCount(Integer linkCount) {
        this.linkCount = linkCount;
    }

    public String getIsShortListed() {
        return isShortListed;
    }


    public void setIsShortListed(String isShortListed) {
        this.isShortListed = isShortListed;
    }

    public String getEvalutedByTS() {
        return evalutedByTS;
    }

    public void setEvalutedByTS(String evalutedByTS) {
        this.evalutedByTS = evalutedByTS;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCandidateEnableDisable() {
        return candidateEnableDisable;
    }

    public void setCandidateEnableDisable(String candidateEnableDisable) {

        this.candidateEnableDisable = candidateEnableDisable;
    }

    public String getScreenedStatus() {
        return screenedStatus;
    }

    public void setScreenedStatus(String screenedStatus) {
        this.screenedStatus = screenedStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCandidateLink() {
        return candidateLink;
    }
    public void setCandidateLink(String candidateLink) {
        this.candidateLink = candidateLink;
    }

    public String getLinkValidity() {
        return linkValidity;
    }
    public void setLinkValidity(String linkValidity) {
        this.linkValidity = linkValidity;
    }




    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "positionId")
    private Positions positions;

    public Positions getPositions() {
        return positions;
    }

    public void setPositions(Positions positions) {
        this.positions = positions;
    }


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "candidateId")
    private Candidates candidates;


    public Candidates getCandidates() {
        return candidates;
    }

    public void setCandidates(Candidates candidates) {
        this.candidates = candidates;
    }
}
