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

public class Positions extends BaseEntity{

    public Positions() {
    }

    public Positions(Integer positionId,String clientName, String contactPerson, String duration, String startDate, String clientLocation, String broadcastLocation,
                     String typeOfReq, String priority, String jobTitle, String openPositions, String addNoMoreCandidates, String billRate, String payRate,
                     String jobDescription, String primarySkill, String secondarySkill, String isQuestionAdded,
                     String recruiter, String technicalScreener, String accountManager,
                     String status, String readyForInterview, String billRatePeriod, String durationPeriod, String payRatePeriod,
                     String isApprovedCandidateEmailSent, String isCandidateAnswered) {
        this.positionId=positionId;
        this.clientName = clientName;
        this.contactPerson = contactPerson;
        this.duration = duration;
        this.startDate = startDate;
        this.clientLocation = clientLocation;
        this.broadcastLocation = broadcastLocation;
        this.typeOfReq = typeOfReq;
        this.priority = priority;
        this.jobTitle = jobTitle;
        this.openPositions = openPositions;
        this.addNoMoreCandidates = addNoMoreCandidates;
        this.billRate = billRate;
        this.payRate = payRate;
        this.jobDescription = jobDescription;
        this.primarySkill = primarySkill;
        this.secondarySkill = secondarySkill;
        this.isQuestionAdded = isQuestionAdded;
        this.recruiter = recruiter;
        this.technicalScreener = technicalScreener;
        this.accountManager = accountManager;
        this.status = status;
        this.readyForInterview = readyForInterview;
        this.billRatePeriod = billRatePeriod;
        this.durationPeriod = durationPeriod;
        this.payRatePeriod = payRatePeriod;
        this.isApprovedCandidateEmailSent = isApprovedCandidateEmailSent;
        this.isCandidateAnswered = isCandidateAnswered;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="positionId" ,insertable = true, nullable = false, unique = true, updatable = true)
    protected Integer positionId;

    @Column(length = 255, nullable = false)
    private String clientName;

    @Column(length = 255, nullable = false)
    private String contactPerson;

    @Column(length = 255, nullable = true)
    private String duration;

    @Column(length = 255, nullable = true)
    private String startDate;

    @Column(length = 255, nullable = true)
    private String clientLocation;

    @Column(length = 255, nullable = true)
    private String broadcastLocation;

    @Column(length = 255, nullable = true)
    private String typeOfReq;

    @Column(length = 255, nullable = true)
    private String priority;

    @Column(length = 255, nullable = true)
    private String jobTitle;

    @Column(length = 255, nullable = true)
    private String openPositions;

    @Column(length = 255, nullable = true)
    private String addNoMoreCandidates;

    @Column(length = 255, nullable = true)
    private String billRate;

    @Column(length = 255, nullable = true)
    private String payRate;

    @Column(length = 5000, nullable = true)
    private String jobDescription;

    @Column(length = 255, nullable = true)
    private String primarySkill;

    @Column(length = 255, nullable = true)
    private String secondarySkill;

    @Column(length = 255, nullable = true)
    private String clientAddress;

    @Column(length = 255, nullable = true)
    private String isQuestionAdded;

    @Column(length = 255, nullable = true)
    private String clientAddress2;

    @Column(length = 255, nullable = true)
    private String clientCity;

    @Column(length = 255, nullable = true)
    private String clientState;

    @Column(length = 255, nullable = true)
    private String clientCountry;

    @Column(length = 255, nullable = true)
    private String recruiter;

    @Column(length = 255, nullable = true)
    private String technicalScreener;

    @Column(length = 255, nullable = true)
    private String accountManager;

    @Column(length = 255, nullable = true)
    private String salesManager;

    @Column(length = 255, nullable = true)
    private String status;

    @Column(length = 255, nullable = true)
    private String readyForInterview;

    @Transient
    private String billRatePeriod;

    @Transient
    private String durationPeriod;

    @Transient
    private String payRatePeriod;

    @Transient
    private String isApprovedCandidateEmailSent;

    @Transient
    private String isCandidateAnswered;

    @Transient
    private String bidRate;

    @Transient
    private String bidResponseTime;

    @Transient
    private Integer bidId;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany( cascade = CascadeType.REMOVE)
    @JoinTable( name="client_position",
            joinColumns=@JoinColumn(name="position_id")) //its optional using for name configuration of the join table
    private Collection<ClientDetails> clientDetails = new HashSet<ClientDetails>();

    @ManyToMany(targetEntity = User.class , fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "technical_screener_positions",
            joinColumns = @JoinColumn(name = "position_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @GenericGenerator(name="increment", strategy = "increment")
    @CollectionId(columns = @Column(name="technical_screener_positions_id"),
            type=@Type(type="long"),generator = "increment"
    )
    private Collection<User> technicalScreenerPositions=new HashSet<User>();


    @ManyToMany(targetEntity = User.class , fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "recruiter_positions",
            joinColumns = @JoinColumn(name = "position_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @GenericGenerator(name="increment", strategy = "increment")
    @CollectionId(columns = @Column(name="recruiter_position_Id"),
            type=@Type(type="long"),generator = "increment"
    )
    private Collection<User> recruiterPositions=new HashSet<User>();



    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getClientLocation() {
        return clientLocation;
    }

    public void setClientLocation(String clientLocation) {
        this.clientLocation = clientLocation;
    }

    public String getBroadcastLocation() {
        return broadcastLocation;
    }

    public void setBroadcastLocation(String broadcastLocation) {
        this.broadcastLocation = broadcastLocation;
    }

    public String getTypeOfReq() {
        return typeOfReq;
    }

    public void setTypeOfReq(String typeOfReq) {
        this.typeOfReq = typeOfReq;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getOpenPositions() {
        return openPositions;
    }

    public void setOpenPositions(String openPositions) {
        this.openPositions = openPositions;
    }

    public String getAddNoMoreCandidates() {
        return addNoMoreCandidates;
    }

    public void setAddNoMoreCandidates(String addNoMoreCandidates) {
        this.addNoMoreCandidates = addNoMoreCandidates;
    }

    public String getBillRate() {
        return billRate;
    }

    public void setBillRate(String billRate) {
        this.billRate = billRate;
    }

    public String getPayRate() {
        return payRate;
    }

    public void setPayRate(String payRate) {
        this.payRate = payRate;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getPrimarySkill() {
        return primarySkill;
    }

    public void setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
    }

    public String getSecondarySkill() {
        return secondarySkill;
    }

    public void setSecondarySkill(String secondarySkill) {
        this.secondarySkill = secondarySkill;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getIsQuestionAdded() {
        return isQuestionAdded;
    }

    public void setIsQuestionAdded(String isQuestionAdded) {
        this.isQuestionAdded = isQuestionAdded;
    }

    public String getClientAddress2() {
        return clientAddress2;
    }

    public void setClientAddress2(String clientAddress2) {
        this.clientAddress2 = clientAddress2;
    }

    public String getClientCity() {
        return clientCity;
    }

    public void setClientCity(String clientCity) {
        this.clientCity = clientCity;
    }

    public String getClientState() {
        return clientState;
    }

    public void setClientState(String clientState) {
        this.clientState = clientState;
    }

    public String getClientCountry() {
        return clientCountry;
    }

    public void setClientCountry(String clientCountry) {
        this.clientCountry = clientCountry;
    }

    public String getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(String recruiter) {
        this.recruiter = recruiter;
    }

    public String getTechnicalScreener() {
        return technicalScreener;
    }

    public void setTechnicalScreener(String technicalScreener) {
        this.technicalScreener = technicalScreener;
    }

    public String getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(String accountManager) {
        this.accountManager = accountManager;
    }

    public String getSalesManager() {
        return salesManager;
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

    public String getReadyForInterview() {
        return readyForInterview;
    }

    public void setReadyForInterview(String readyForInterview) {
        this.readyForInterview = readyForInterview;
    }

    public String getBillRatePeriod() {
        return billRatePeriod;
    }

    public void setBillRatePeriod(String billRatePeriod) {
        this.billRatePeriod = billRatePeriod;
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

    public String getIsApprovedCandidateEmailSent() {
        return isApprovedCandidateEmailSent;
    }

    public void setIsApprovedCandidateEmailSent(String isApprovedCandidateEmailSent) {
        this.isApprovedCandidateEmailSent = isApprovedCandidateEmailSent;
    }

    public String getIsCandidateAnswered() {
        return isCandidateAnswered;
    }

    public void setIsCandidateAnswered(String isCandidateAnswered) {
        this.isCandidateAnswered = isCandidateAnswered;
    }

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

    public Collection<ClientDetails> getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(Collection<ClientDetails> clientDetails) {
        this.clientDetails = clientDetails;
    }

    public Collection<User> getTechnicalScreenerPositions() {
        return technicalScreenerPositions;
    }

    public void setTechnicalScreenerPositions(Collection<User> technicalScreenerPositions) {
        this.technicalScreenerPositions = technicalScreenerPositions;
    }

    public Collection<User> getRecruiterPositions() {
        return recruiterPositions;
    }

    public void setRecruiterPositions(Collection<User> recruiterPositions) {
        this.recruiterPositions = recruiterPositions;
    }
}
