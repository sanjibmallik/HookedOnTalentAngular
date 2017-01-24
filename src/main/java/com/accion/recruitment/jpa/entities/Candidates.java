package com.accion.recruitment.jpa.entities;


import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 09/01/17 00:11 AM#$
 */

@Entity
@Table(name = "candidates")

public class Candidates extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="candidateId" ,insertable = true, nullable = false, unique = true, updatable = true)
    protected Integer candidateId;

    @Column(length = 255, nullable = true)
    private String firstName;

    @Column(length = 255, nullable = true)
    private String middleName;

    @Column(length = 255, nullable = true)
    private String lastName;

    @Column(length = 255, nullable = true)
    private String candidateName;

    @Column(length = 255, nullable = true)
    private String cellPhoneNumber;

    @Column(length = 255, nullable = true)
    private String homePhoneNumber;

    @Column(length = 255, nullable = true)
    private String emailId;

    @Column(length = 255, nullable = true)
    private String jobTitle;

    @Column(length = 255, nullable = true)
    private String primarySkill;

    @Column(length = 255, nullable = true)
    private String secondarySkill;

    @Column(length = 255, nullable = true)
    private String currentLocation;

    @Column(length = 255, nullable = true)
    private String totalExperience;

    @Column(length = 255, nullable = true)
    private String totalExperiencePeriod;

    @Column(length = 255, nullable = true)
    private String usExperience;

    @Column(length = 255, nullable = true)
    private String usExperiencePeriod;

    @Column(length = 255, nullable = true)
    private String address;

    @Column(length = 255, nullable = true)
    private String immigrationStatus;

    @Column(length = 255, nullable = true)
    private String address2;

    @Column(length = 255, nullable = true)
    private String city;

    @Column(length = 255, nullable = true)
    private String state;

    @Column(length = 255, nullable = true)
    private String country;

    @Column(length = 255, nullable = true)
    private String currentEmployer;

    @Column(length = 255, nullable = true)
    private String status;

    @Column(length = 255, nullable = true)
    private String billRate;

    @Column(length = 255, nullable = true)
    private String billRateCurrency;

    @Column(length = 255, nullable = true)
    private String payRate;

    @Column(length = 255, nullable = true)
    private String payRateCurrency;

    @Column(length = 5000)
    private String note;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] resume;

    @Column(length = 255, nullable = true)
    private String resumeType;

    @Column(length = 255, nullable = true)
    private String originalFileName;

    @Transient
    private String allReadyAdded;

    @Transient
    private String evaluatedByTS;

    @Transient
    private  String finalVerdict;

    @Transient
    private  Integer linkCount;

    @Transient
    private  String addedBy;

    @Transient
    public String isShortListed;

    @Transient
    private String screenedStatus;

    @Transient
    private String enableDisableStatus;

    @Transient
    @Column(length = 4000)
    private String comment;

    @Transient
    private String interviewStatus;

    @Transient
    private String score;

    @Transient
    private Integer positionId;

    @Transient
    private String positionName;

    @OneToMany(mappedBy = "candidates",fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<PositionCandidates> positionCandidatesSet = new HashSet<PositionCandidates>();

    @OneToMany(mappedBy = "candidatePastHistory",fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<CandidatePastHistory> candidatePastHistorySet = new HashSet<CandidatePastHistory>();

    @ManyToMany(targetEntity = User.class , fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "recruiter_candidates",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @GenericGenerator(name="increment", strategy = "increment")
    @CollectionId(columns = @Column(name="recruiter_candidates_id"),
            type=@Type(type="long"),generator = "increment"
    )
    private Collection<User> recruiterCandidates=new HashSet<User>();


    public void addUserGroup(PositionCandidates positionCandidates) {
        this.positionCandidatesSet.add(positionCandidates);
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(String totalExperience) {
        this.totalExperience = totalExperience;
    }

    public String getTotalExperiencePeriod() {
        return totalExperiencePeriod;
    }

    public void setTotalExperiencePeriod(String totalExperiencePeriod) {
        this.totalExperiencePeriod = totalExperiencePeriod;
    }

    public String getUsExperience() {
        return usExperience;
    }

    public void setUsExperience(String usExperience) {
        this.usExperience = usExperience;
    }

    public String getUsExperiencePeriod() {
        return usExperiencePeriod;
    }

    public void setUsExperiencePeriod(String usExperiencePeriod) {
        this.usExperiencePeriod = usExperiencePeriod;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImmigrationStatus() {
        return immigrationStatus;
    }

    public void setImmigrationStatus(String immigrationStatus) {
        this.immigrationStatus = immigrationStatus;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrentEmployer() {
        return currentEmployer;
    }

    public void setCurrentEmployer(String currentEmployer) {
        this.currentEmployer = currentEmployer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBillRate() {
        return billRate;
    }

    public void setBillRate(String billRate) {
        this.billRate = billRate;
    }

    public String getBillRateCurrency() {
        return billRateCurrency;
    }

    public void setBillRateCurrency(String billRateCurrency) {
        this.billRateCurrency = billRateCurrency;
    }

    public String getPayRate() {
        return payRate;
    }

    public void setPayRate(String payRate) {
        this.payRate = payRate;
    }

    public String getPayRateCurrency() {
        return payRateCurrency;
    }

    public void setPayRateCurrency(String payRateCurrency) {
        this.payRateCurrency = payRateCurrency;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public byte[] getResume() {
        return resume;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public String getResumeType() {
        return resumeType;
    }

    public void setResumeType(String resumeType) {
        this.resumeType = resumeType;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getAllReadyAdded() {
        return allReadyAdded;
    }

    public void setAllReadyAdded(String allReadyAdded) {
        this.allReadyAdded = allReadyAdded;
    }

    public String getEvaluatedByTS() {
        return evaluatedByTS;
    }

    public void setEvaluatedByTS(String evaluatedByTS) {
        this.evaluatedByTS = evaluatedByTS;
    }

    public String getFinalVerdict() {
        return finalVerdict;
    }

    public void setFinalVerdict(String finalVerdict) {
        this.finalVerdict = finalVerdict;
    }

    public Integer getLinkCount() {
        return linkCount;
    }

    public void setLinkCount(Integer linkCount) {
        this.linkCount = linkCount;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getIsShortListed() {
        return isShortListed;
    }

    public void setIsShortListed(String isShortListed) {
        this.isShortListed = isShortListed;
    }

    public String getScreenedStatus() {
        return screenedStatus;
    }

    public void setScreenedStatus(String screenedStatus) {
        this.screenedStatus = screenedStatus;
    }

    public String getEnableDisableStatus() {
        return enableDisableStatus;
    }

    public void setEnableDisableStatus(String enableDisableStatus) {
        this.enableDisableStatus = enableDisableStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(String interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
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

    public Set<PositionCandidates> getPositionCandidatesSet() {
        return positionCandidatesSet;
    }

    public void setPositionCandidatesSet(Set<PositionCandidates> positionCandidatesSet) {
        this.positionCandidatesSet = positionCandidatesSet;
    }

    public Set<CandidatePastHistory> getCandidatePastHistorySet() {
        return candidatePastHistorySet;
    }

    public void setCandidatePastHistorySet(Set<CandidatePastHistory> candidatePastHistorySet) {
        this.candidatePastHistorySet = candidatePastHistorySet;
    }

    public Collection<User> getRecruiterCandidates() {
        return recruiterCandidates;
    }

    public void setRecruiterCandidates(Collection<User> recruiterCandidates) {
        this.recruiterCandidates = recruiterCandidates;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        } else if (!(o instanceof Candidates )) {
            return false;
        } else {
            return ((Candidates) o).getCandidateId().equals(this.candidateId) ;
        }
    }
}
