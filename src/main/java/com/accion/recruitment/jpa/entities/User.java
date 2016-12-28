package com.accion.recruitment.jpa.entities;

import org.hibernate.annotations.*;
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
public class User extends BaseEntity {

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

    @Column(length = 255, nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean enabled=Boolean.TRUE;

    @Column(length = 12, nullable = false)
    private Long contactNumber;

    @Column(length = 255, nullable = false)
    private String role;

    @Column(length = 12, nullable = true)
    private Long alternateContact;

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
    private Collection<TechnicalScreenerSkills> technicalScreenerDetailsDSkillsSet=new HashSet<>();


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

    public Long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getAlternateContact() {
        return alternateContact;
    }

    public void setAlternateContact(Long alternateContact) {
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
        return technicalScreenerDetailsDSkillsSet;
    }

    public void setTechnicalScreenerDetailsDSkillsSet(Collection<TechnicalScreenerSkills> technicalScreenerDetailsDSkillsSet) {
        this.technicalScreenerDetailsDSkillsSet = technicalScreenerDetailsDSkillsSet;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", email='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", groupsSet=" + groupsSet +
                '}';
    }




}
