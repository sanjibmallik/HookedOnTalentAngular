package com.accion.recruitment.jpa.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/23/16 00:11 AM#$
 */

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User extends BaseEntity {

    public User() {
    }

    public User(Integer id,String userName, String emailId, String firstName, String lastName,String contactNumber,String role ,Boolean enabled, String errorMessage ) {
        this.id=id;
        this.userName = userName;
        this.emailId = emailId;
        this.enabled = enabled;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.errorMessage = errorMessage;
        this.role = role;
    }



    public User(Integer id,String firstName, String lastName, String userName, String emailId, Boolean enabled, String contactNumber, String role, String alternateContact, String addressOne, String addressTwo, Long zipCode, String city, String state, String country, Long expectedPayRange, byte[] userImage, byte[] userProfile, String errorMessage,Collection<TechnicalScreenerSkills> technicalScreenerDetailsSkillsSet) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.emailId = emailId;
        this.enabled = enabled;
        this.contactNumber = contactNumber;
        this.role = role;
        this.alternateContact = alternateContact;
        this.addressOne = addressOne;
        this.addressTwo = addressTwo;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.country = country;
        this.expectedPayRange = expectedPayRange;
        this.userImage = userImage;
        this.userProfile = userProfile;
        this.errorMessage = errorMessage;
        this.technicalScreenerDetailsSkillsSet = technicalScreenerDetailsSkillsSet;
    }

    public User(Integer id,String firstName, String lastName) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id" ,insertable = true, nullable = false, unique = true, updatable = true)
    protected Integer id;

    @Column(length = 32, nullable = false)
    private String firstName;

    @Column(length = 32, nullable = false)
    private String lastName;

    @Column(unique = true, length = 32, nullable = false)
    private String userName;

    @Column(length = 255, nullable = false)
    private String emailId;

    @Column(unique = true,length = 255, nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean enabled=Boolean.TRUE;

    @Column(unique = true,length = 12, nullable = false)
    private String contactNumber;

    @Column(length = 255, nullable = false)
    private String role;

    @Column(length = 12, nullable = true)
    private String alternateContact;

    @Column(length = 255, nullable = true)
    private String addressOne;

    @Column(length = 255, nullable = true)
    private String addressTwo;

    @Column(length = 10, nullable = true)
    private Long zipCode;

    @Column(length = 255, nullable = true)
    private String city;

    @Column(length = 255, nullable = true)
    private String state;

    @Column(length = 255, nullable = true)
    private String country;

    @Column(length = 255, nullable = true)
    private Long expectedPayRange;

    @Lob
    @Column(name = "userImage",length = 20971520,  columnDefinition = "mediumblob")
    private byte[] userImage;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] userProfile;

    @Transient
    private String errorMessage;


    @ManyToMany(mappedBy = "userSet"
            , targetEntity = Groups.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @org.hibernate.annotations.Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private Set<Groups> groupsSet=new HashSet<Groups>();

    @ManyToMany(targetEntity = TechnicalScreenerSkills.class, fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinTable(name = "user_skill_details" ,
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "technicalScreener_skills_id"))
    @GenericGenerator(name="increment", strategy = "increment")
    @CollectionId(columns = @Column(name="ts_skill_details_id"),
            type=@Type(type="long"),generator = "increment"
    )
    private Collection<TechnicalScreenerSkills> technicalScreenerDetailsSkillsSet=new HashSet<TechnicalScreenerSkills>();


    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany( cascade = CascadeType.REMOVE)
    @JoinTable( name="account_manager_clients",

            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "clientDetails_id"))
    private Collection<ClientDetails> accountManagerClients = new HashSet<ClientDetails>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany( cascade = CascadeType.REMOVE)
    @JoinTable( name="account_manager_positions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id"))
    private Collection<Positions> accountManagerPositions = new HashSet<Positions>();

    @ManyToMany(mappedBy = "technicalScreenerPositions"
            , targetEntity = Positions.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private Set<Positions> technicalScreenerPositions=new HashSet<Positions>();

    @ManyToMany(mappedBy = "recruiterPositions"
            , targetEntity = Positions.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private Set<Positions> recruiterPositions=new HashSet<Positions>();

    @ManyToMany(mappedBy = "recruiterCandidates"
            , targetEntity = Candidates.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private Set<Candidates> recruiterCandidates=new HashSet<Candidates>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAlternateContact() {
        return alternateContact;
    }

    public void setAlternateContact(String alternateContact) {
        this.alternateContact = alternateContact;
    }

    public String getAddressOne() {
        return addressOne;
    }

    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public Long getZipCode() {
        return zipCode;
    }

    public void setZipCode(Long zipCode) {
        this.zipCode = zipCode;
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

    public Long getExpectedPayRange() {
        return expectedPayRange;
    }

    public void setExpectedPayRange(Long expectedPayRange) {
        this.expectedPayRange = expectedPayRange;
    }

    public byte[] getUserImage() {
        return userImage;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }

    public byte[] getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(byte[] userProfile) {
        this.userProfile = userProfile;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Set<Groups> getGroupsSet() {
        return groupsSet;
    }

    public void setGroupsSet(Set<Groups> groupsSet) {
        this.groupsSet = groupsSet;
    }

    public Collection<TechnicalScreenerSkills> getTechnicalScreenerDetailsDSkillsSet() {
        return technicalScreenerDetailsSkillsSet;
    }

    public void setTechnicalScreenerDetailsDSkillsSet(Collection<TechnicalScreenerSkills> technicalScreenerDetailsSkillsSet) {
        this.technicalScreenerDetailsSkillsSet = technicalScreenerDetailsSkillsSet;
    }

    public Collection<ClientDetails> getAccountManagerClients() {
        return accountManagerClients;
    }

    public void setAccountManagerClients(Collection<ClientDetails> accountManagerClients) {
        this.accountManagerClients = accountManagerClients;
    }

    public Collection<Positions> getAccountManagerPositions() {
        return accountManagerPositions;
    }

    public void setAccountManagerPositions(Collection<Positions> accountManagerPositions) {
        this.accountManagerPositions = accountManagerPositions;
    }

    public Set<Positions> getTechnicalScreenerPositions() {
        return technicalScreenerPositions;
    }

    public void setTechnicalScreenerPositions(Set<Positions> technicalScreenerPositions) {
        this.technicalScreenerPositions = technicalScreenerPositions;
    }

    public Set<Positions> getRecruiterPositions() {
        return recruiterPositions;
    }

    public void setRecruiterPositions(Set<Positions> recruiterPositions) {
        this.recruiterPositions = recruiterPositions;
    }

    public Set<Candidates> getRecruiterCandidates() {
        return recruiterCandidates;
    }

    public void setRecruiterCandidates(Set<Candidates> recruiterCandidates) {
        this.recruiterCandidates = recruiterCandidates;
    }

    @Override
    public String toString() {
        return "{'id':" + id    +
                ",'userName':'" + userName + '\'' +
                ", 'emailId':'" + emailId + '\'' +
                ", 'enabled':'" + enabled + '\'' +
                ", 'firstName':'" + firstName + '\'' +
                ", 'lastName':'" + lastName + '\'' +
                ", 'contactNumber':" + contactNumber +
                ", 'role':'" + role + '\'' +
                ", 'errorMessage':'" + errorMessage + '\'' + "}";

    }

}
