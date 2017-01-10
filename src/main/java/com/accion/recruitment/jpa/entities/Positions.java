package com.accion.recruitment.jpa.entities;


import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;


/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 09/01/17 00:11 AM#$
 */


@Entity
@Table(name = "positions")

public class Positions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="positionId" ,insertable = true, nullable = false, unique = true, updatable = true)
    protected Integer positionId;

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", nullable = true)
    protected Date createdDate ;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    private String createdBy;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    private String clientName;

    private String contactPerson;

    @Transient
    private String durationPeriod;

    private String duration;

    private String startDate;

    private String clientLocation;

    private String broadcastLocation;

    private String typeOfReq;

    private String priority;

    private String jobTitle;

    private String openPositions;

    private String addNoMoreCandidates;

    public String getAddNoMoreCandidates() {
        return addNoMoreCandidates;
    }

    public void setAddNoMoreCandidates(String addNoMoreCandidates) {
        this.addNoMoreCandidates = addNoMoreCandidates;
    }

    @Transient
    private String billRatePeriod;

    private String billRate;


    @Transient
    private String payRatePeriod;

    private String payRate;

    @Transient
    private String isApprovedCandidateEmailSent;

    public String getIsApprovedCandidateEmailSent() {
        return isApprovedCandidateEmailSent;
    }

    public void setIsApprovedCandidateEmailSent(String isApprovedCandidateEmailSent) {
        this.isApprovedCandidateEmailSent = isApprovedCandidateEmailSent;
    }

    @Column(length = 5000)
    private String jobDescription;

    private String primarySkill;

    private String secondarySkill;

    private String clientAddress;

    private String isQustionAdded;
    @Transient
    private String isCandidateAnswered;

    public String getIsCandidateAnswered() {
        return isCandidateAnswered;
    }

    public void setIsCandidateAnswered(String isCandidateAnswered) {
        this.isCandidateAnswered = isCandidateAnswered;
    }

    public String getIsQustionAdded() {
        return isQustionAdded;
    }

    public void setIsQustionAdded(String isQustionAdded) {
        this.isQustionAdded = isQustionAdded;
    }




    public String getClientAddress2() {
        return clientAddress2;
    }

    public void setClientAddress2(String clientAddress2) {
        this.clientAddress2 = clientAddress2;
    }

    private String clientAddress2;

    private String clientCity;

    private String clientState;

    private String clientCountry;

    private String recruiter;

    private String technicalScreener;

    private String accountManager;

    private String salesManager;

    private String status;

    private String readyForInterview;

    public String getReadyForInterview() {
        return readyForInterview;
    }

    public void setReadyForInterview(String readyForInterview) {
        this.readyForInterview = readyForInterview;
    }



    @Transient
    private String bidRate;

    @Transient
    private String bidResponseTime;
    @Transient
    private Integer bidId;

/*

    @OneToMany(mappedBy = "positions",targetEntity = PositionCandidates.class,fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private Set<PositionCandidates> positionCandidatesSet = new HashSet<>();
    public Set<PositionCandidates> getPositionCandidatesSet() {
        return positionCandidatesSet;
    }

*/


    public String getBidRate() {
        return bidRate;
    }

    public void setBidRate(String bidRate) {
        this.bidRate = bidRate;
    }

    public String getBidResponseTime() {
        return bidResponseTime;
    }

    public void setBidResponseTime(String bidResponseTime) {
        this.bidResponseTime = bidResponseTime;
    }

    public Integer getBidId() {
        return bidId;
    }

    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    /*@LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany( fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinTable( name="position_bidding",
            joinColumns=@JoinColumn(name="position_id")) //its optional using for name configuration of the join table
    @GenericGenerator(name="increment", strategy = "increment")
    @CollectionId(columns = @Column(name="position_bidding_Id"),
            type=@Type(type="long"),generator = "increment"
    )

    private Collection<Bidding> bidding = new HashSet<>();

    public Collection<Bidding> getBidding() {
        return bidding;
    }

    public void setBidding(Collection<Bidding> bidding) {
        this.bidding = bidding;
    }*/


    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany( cascade = CascadeType.REMOVE)
    @JoinTable( name="client_position",
            joinColumns=@JoinColumn(name="position_id")) //its optional using for name configuration of the join table
    private Collection<ClientDetails> clientDetails = new HashSet<ClientDetails>();

    public Collection<ClientDetails> getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(Collection<ClientDetails> clientDetails) {
        this.clientDetails = clientDetails;
    }


    @ManyToMany(targetEntity = User.class , fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "technical_screener_positions",
            joinColumns = @JoinColumn(name = "position_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @GenericGenerator(name="increment", strategy = "increment")
    @CollectionId(columns = @Column(name="technical_screener_positions_id"),
            type=@Type(type="long"),generator = "increment"
    )
    private Collection<User> technicalScreenerPositions=new HashSet<User>();

    public Collection<User> getTechnicalScreenerPositions() {
        return technicalScreenerPositions;
    }

    public void setTechnicalScreenerPositions(Collection<User> technicalScreenerPositions) {
        this.technicalScreenerPositions = technicalScreenerPositions;
    }





    @ManyToMany(targetEntity = User.class , fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "recruiter_positions",
            joinColumns = @JoinColumn(name = "position_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @GenericGenerator(name="increment", strategy = "increment")
    @CollectionId(columns = @Column(name="recruiter_position_Id"),
            type=@Type(type="long"),generator = "increment"
    )
    private Collection<User> recruiterPositions=new HashSet<User>();

    public Collection<User> getRecruiterPositions() {
        return recruiterPositions;
    }

    public void setRecruiterPositions(Collection<User> recruiterPositions) {
        this.recruiterPositions = recruiterPositions;
    }

/*

    @ManyToMany(mappedBy = "generalQuestionPositionsSet"
            , targetEntity = GeneralQuestion.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.ALL })
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private Set<GeneralQuestion> generalQuestionPosition=new HashSet<>();

    public Set<GeneralQuestion> getGeneralQuestionPosition() {
        return generalQuestionPosition;
    }

    public void setGeneralQuestionPosition(Set<GeneralQuestion> generalQuestionPosition) {
        this.generalQuestionPosition = generalQuestionPosition;
    }
*/

/*
    @ManyToMany(mappedBy = "technicalQuestionPositionsSet"
            , targetEntity = TechnicalQuestion.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private Set<TechnicalQuestion> technicalQuestionPosition=new HashSet<>();

    public Set<TechnicalQuestion> getTechnicalQuestionPosition() {
        return technicalQuestionPosition;
    }

    public void setTechnicalQuestionPosition(Set<TechnicalQuestion> technicalQuestionPosition) {
        this.technicalQuestionPosition = technicalQuestionPosition;
    }

    @ManyToMany(mappedBy = "videoQuestionPositionsSet"
            , targetEntity = VideoQuestion.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private Set<VideoQuestion> videoQuestionPosition=new HashSet<>();

    public Set<VideoQuestion> getVideoQuestionPosition() {
        return videoQuestionPosition;
    }

    public void setVideoQuestionPosition(Set<VideoQuestion> videoQuestionPosition) {
        this.videoQuestionPosition = videoQuestionPosition;
    }
*/

    public String getClientName() {
        return clientName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getDuration() {

        return duration;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getClientLocation() {
        return clientLocation;
    }

    public String getBroadcastLocation() {
        return broadcastLocation;
    }

    public String getTypeOfReq() {
        return typeOfReq;
    }

    public String getPriority() {
        return priority;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getOpenPositions() {
        return openPositions;
    }

    public String getBillRate() {
        return billRate;
    }

    public String getPayRate() {
        return payRate;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getPrimarySkill() {
        return primarySkill;
    }

    public String getSecondarySkill() {
        return secondarySkill;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public String getClientCity() {
        return clientCity;
    }

    public String getClientState() {
        return clientState;
    }

    public String getClientCountry() {
        return clientCountry;
    }

    public String getRecruiter() {
        return recruiter;
    }

    public String getTechnicalScreener() {
        return technicalScreener;
    }

    public String getAccountManager() {
        return accountManager;
    }

    public String getSalesManager() {
        return salesManager;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setClientLocation(String clientLocation) {
        this.clientLocation = clientLocation;
    }

    public void setBroadcastLocation(String broadcastLocation) {
        this.broadcastLocation = broadcastLocation;
    }

    public void setTypeOfReq(String typeOfReq) {
        this.typeOfReq = typeOfReq;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setOpenPositions(String openPositions) {
        this.openPositions = openPositions;
    }

    public void setBillRate(String billRate) {
        this.billRate = billRate;
    }

    public void setPayRate(String payRate) {
        this.payRate = payRate;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public void setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
    }

    public void setSecondarySkill(String secondarySkill) {
        this.secondarySkill = secondarySkill;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public void setClientCity(String clientCity) {
        this.clientCity = clientCity;
    }

    public void setClientState(String clientState) {
        this.clientState = clientState;
    }

    public void setClientCountry(String clientCountry) {
        this.clientCountry = clientCountry;
    }

    public void setRecruiter(String recruiter) {
        this.recruiter = recruiter;
    }

    public void setTechnicalScreener(String technicalScreener) {
        this.technicalScreener = technicalScreener;
    }

    public void setAccountManager(String accountManager) {
        this.accountManager = accountManager;
    }

    public void setSalesManager(String salesManager) {
        this.salesManager = salesManager;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDurationPeriod() {
        return durationPeriod;
    }

    public void setDurationPeriod(String durationPeriod) {
        this.durationPeriod = durationPeriod;
    }

    public String getPayRatePeriod() {
        return payRatePeriod;
    }

    public void setPayRatePeriod(String payRatePeriod) {
        this.payRatePeriod = payRatePeriod;
    }


    public String getBillRatePeriod() {
        return billRatePeriod;
    }

    public void setBillRatePeriod(String billRatePeriod) {
        this.billRatePeriod = billRatePeriod;
    }

}
