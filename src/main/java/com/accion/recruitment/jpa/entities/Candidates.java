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

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
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


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_DATE", nullable = true)
    protected Date updatedDate ;

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    private String firstName;
    private String middleName;
    private String lastName;
    private String candidateName;
    private String cellPhoneNumber;
    private String homePhoneNumber;
    private String emailId;
    private String jobTitle;
    private String primarySkill;
    private String secondarySkill;
    private String currentLocation;
    private String totalExperience;
    private String totalExperiencePeriod;
    private String usExperience;
    private String usExperiencePeriod;
    private String address;
    private String immigrationStatus;
    @Transient
    private String allReadyAdded;

    public String getAllReadyAdded() {
        return allReadyAdded;
    }

    public void setAllReadyAdded(String allReadyAdded) {
        this.allReadyAdded = allReadyAdded;
    }

    public String getUsExperiencePeriod() {
        return usExperiencePeriod;
    }

    public void setUsExperiencePeriod(String usExperiencePeriod) {
        this.usExperiencePeriod = usExperiencePeriod;
    }

    public String getImmigrationStatus() {
        return immigrationStatus;
    }

    public void setImmigrationStatus(String immigrationStatus) {
        this.immigrationStatus = immigrationStatus;
    }

    @Transient
    private String evalutedByTS;

    @Transient
    private  String finalVerdict;

    public String getFinalVerdict() {
        return finalVerdict;
    }

    public void setFinalVerdict(String finalVerdict) {
        this.finalVerdict = finalVerdict;
    }

    @Transient
    private  Integer linkCount;

    @Transient
    private  String addedBy;

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

    public String getEvalutedByTS() {
        return evalutedByTS;
    }

    public void setEvalutedByTS(String evalutedByTS) {
        this.evalutedByTS = evalutedByTS;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    @Transient
    public String isShortListed;

    public String getIsShortListed() {
        return isShortListed;
    }

    public void setIsShortListed(String isShortListed) {
        this.isShortListed = isShortListed;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getTotalExperiencePeriod() {
        return totalExperiencePeriod;
    }

    public void setTotalExperiencePeriod(String totalExperiencePeriod) {
        this.totalExperiencePeriod = totalExperiencePeriod;
    }

    private String address2;
    private String city;
    private String state;
    private String country;
    private String currentEmployer;
    private String status;
    private String billRate;
    private String billRateCurrency;

    private String payRate;
    private String payRateCurrency;

    @Column(length = 5000)
    private String note;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBillRateCurrency() {
        return billRateCurrency;
    }

    public void setBillRateCurrency(String billRateCurrency) {
        this.billRateCurrency = billRateCurrency;
    }

    public String getPayRateCurrency() {
        return payRateCurrency;
    }

    public void setPayRateCurrency(String payRateCurrency) {
        this.payRateCurrency = payRateCurrency;
    }

    @Transient
    private String screenedStatus;

    @Transient
    private String enableDisableStatus;


    @Transient
    @Column(length = 4000)
    private String comment;

    @Transient
    private String interviewStatus;

    public String getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(String interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    @Transient
    private String score;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] resume;

    private String resumeType;
    private String originalFileName;

    @Transient
    private Integer positionId;
    @Transient
    private String positionName;

    @OneToMany(mappedBy = "candidates",fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<PositionCandidates> positionCandidatesSet = new HashSet<PositionCandidates>();
    public Set<PositionCandidates> getPositionCandidatesSet() {
        return positionCandidatesSet;
    }

    public void setPositionCandidatesSet(Set<PositionCandidates> positionCandidatesSet) {
        this.positionCandidatesSet = positionCandidatesSet;
    }

    public void addUserGroup(PositionCandidates positionCandidates) {
        this.positionCandidatesSet.add(positionCandidates);
    }

    @OneToMany(mappedBy = "candidatePastHistory",fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<CandidatePastHistory> candidatePastHistorySet = new HashSet<CandidatePastHistory>();

    public void setCandidatePastHistorySet(Set<CandidatePastHistory> candidatePastHistorySet) {
        this.candidatePastHistorySet = candidatePastHistorySet;
    }

    public Set<CandidatePastHistory> getCandidatePastHistorySet() {
        return candidatePastHistorySet;
    }

    @ManyToMany(targetEntity = User.class , fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "recruiter_candidates",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @GenericGenerator(name="increment", strategy = "increment")
    @CollectionId(columns = @Column(name="recruiter_candidates_id"),
            type=@Type(type="long"),generator = "increment"
    )
    private Collection<User> recruiterCandidates=new HashSet<User>();

    public Collection<User> getRecruiterCandidates() {
        return recruiterCandidates;
    }

    public void setRecruiterCandidates(Collection<User> recruiterCandidates) {
        this.recruiterCandidates = recruiterCandidates;
    }


/*@ManyToMany(targetEntity = Positions.class , fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "position_candidates")

    private Collection<Positions> positionsSet=new HashSet<>();


    public Collection<Positions> getPositionsSet() {
        return positionsSet;
    }

    public void setPositionsSet(Collection<Positions> positionsSet) {
        this.positionsSet = positionsSet;
    }


    public void setPositions(Positions positions) {
        this.positionsSet.add(positions) ;
    }
*/

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
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

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getResumeType() {
        return resumeType;
    }

    public void setResumeType(String resumeType) {
        this.resumeType = resumeType;
    }

    public byte[] getResume() {
        return resume;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public String getStatus() {
        return status;
    }



    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getPrimarySkill() {
        return primarySkill;
    }

    public String getSecondarySkill() {
        return secondarySkill;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public String getTotalExperience() {
        return totalExperience;
    }

    public String getUsExperience() {
        return usExperience;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getCurrentEmployer() {
        return currentEmployer;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
    }

    public void setSecondarySkill(String secondarySkill) {
        this.secondarySkill = secondarySkill;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setTotalExperience(String totalExperience) {
        this.totalExperience = totalExperience;
    }

    public void setUsExperience(String usExperience) {
        this.usExperience = usExperience;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCurrentEmployer(String currentEmployer) {
        this.currentEmployer = currentEmployer;
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
