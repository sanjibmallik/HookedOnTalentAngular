package com.accion.recruitment.jpa.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by AL1451 on 5/9/16.
 */
@Entity
@Table(name = "technicalscreener")
public class TechnicalScreener {

   /* public TechnicalScreener() {
        super(TechnicalScreener.class);
    }
*/
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private Boolean enabled=Boolean.TRUE;
    private String primarySkills;
    private String secondarySkills;
    private String primarySkillsExperience;
    private String secondarySkillsExperience;
    private String expectedPayRange;
    public String getSecondarySkillsExperience() {
        return secondarySkillsExperience;
    }

    public void setSecondarySkillsExperience(String secondarySkillsExperience) {
        this.secondarySkillsExperience = secondarySkillsExperience;
    }

    private String contactNo;
    private String alternateContactNo;
    private String zipCode;
    private String city;
    private String address;
    private String address2;
    private String country;
    private String state;
    private String rating;
    @Column(length = 4000)
    private String comment;
    private byte[] resume;



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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPrimarySkills() {
        return primarySkills;
    }

    public void setPrimarySkills(String primarySkills) {
        this.primarySkills = primarySkills;
    }

    public String getSecondarySkills() {
        return secondarySkills;
    }

    public void setSecondarySkills(String secondarySkills) {
        this.secondarySkills = secondarySkills;
    }

    public String getExpectedPayRange() {
        return expectedPayRange;
    }

    public void setExpectedPayRange(String expectedPayRange) {
        this.expectedPayRange = expectedPayRange;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAlternateContactNo() {
        return alternateContactNo;
    }

    public void setAlternateContactNo(String alternateContactNo) {
        this.alternateContactNo = alternateContactNo;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public byte[] getResume() {
        return resume;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public String getPrimarySkillsExperience() {
        return primarySkillsExperience;
    }

    public void setPrimarySkillsExperience(String primarySkillsExperience) {
        this.primarySkillsExperience = primarySkillsExperience;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
