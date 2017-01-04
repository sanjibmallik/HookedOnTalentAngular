package com.accion.recruitment.jpa.entities;

import javax.persistence.*;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 01/02/17 00:11 AM#$
 */

@Entity
@Table(name="settings")

public class Settings extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id" ,insertable = true, nullable = false, unique = true, updatable = true)
    protected Integer id;

    private String emailId;

    private String emailPassword;

    private String domainName;

    private String bucketURL;

    private  String bucketName;

    private byte[] introductionVideo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getIntroductionVideo() {
        return introductionVideo;
    }

    public void setIntroductionVideo(byte[] introductionVideo) {
        this.introductionVideo = introductionVideo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketURL() {
        return bucketURL;
    }

    public void setBucketURL(String bucketURL) {
        this.bucketURL = bucketURL;
    }
}
