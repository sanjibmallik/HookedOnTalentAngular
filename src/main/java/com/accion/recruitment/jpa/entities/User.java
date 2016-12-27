package com.accion.recruitment.jpa.entities;

import org.hibernate.annotations.*;

import javax.persistence.*;
import java.util.Date;
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

    @Column(unique = true, length = 24, nullable = false)
    private String userName;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean enabled=Boolean.TRUE;

    @Column(length = 24, nullable = false)
    private String firstName;

    @Column(length = 24, nullable = true)
    private String lastName;

    @Column(name="dob")
    private Date dob;

    @Lob
    @Column(name = "userImage",length = 20971520,  columnDefinition = "mediumblob")
    private byte[] userImage;

    private String role;

    @Transient
    private String errorMessage;


    @ManyToMany(mappedBy = "userSet"
            , targetEntity = Groups.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @org.hibernate.annotations.Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private Set<Groups> groupsSet=new HashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public byte[] getUserImage() {
        return userImage;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    /* @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", roles=" + roles +
                ", groupsSet=" + groupsSet +
                '}';
    }

*/


}
