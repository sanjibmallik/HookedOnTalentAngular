package com.accion.recruitment.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 09/01/17 00:11 AM#$
 */

@Entity
@Table(name = "client_details")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ClientDetails extends BaseEntity {

    public ClientDetails() {
    }

    public ClientDetails(Integer id,String clientName) {
        this.id=id;
        this.clientName = clientName;
    }

    public ClientDetails(String clientName, Collection<ClientContacts> clientContacts) {
        this.clientName = clientName;
        this.clientContacts = clientContacts;
    }

    public ClientDetails(Integer id,String clientName, String industry, String engagementModel, String federalId, String faxNumber, String contactNumber, String alternateContact, String addressOne, String addressTwo, String city, String state, String country, Long zipCode, String websiteUrl, Boolean enable, String isUserActive, String owner, String note,  Collection<ClientContacts> clientContacts) {
        this.id=id;
        this.clientName = clientName;
        this.industry = industry;
        this.engagementModel = engagementModel;
        this.federalId = federalId;
        this.faxNumber = faxNumber;
        this.contactNumber = contactNumber;
        this.alternateContact = alternateContact;
        this.addressOne = addressOne;
        this.addressTwo = addressTwo;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.websiteUrl = websiteUrl;
        this.enable = enable;
        this.isUserActive = isUserActive;
        this.owner = owner;
        this.note = note;
        this.clientContacts = clientContacts;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id" ,insertable = true, nullable = false, unique = true, updatable = true)
    protected Integer id;

    @Column(unique = true, length = 24, nullable = false)
    private String clientName;

    @Column(length = 255, nullable = false)
    private String industry;

    @Column(length = 255, nullable = false)
    private String engagementModel;

    @Column(length = 255, nullable = false)
    private String federalId;

    @Column(length = 255, nullable = true)
    private String faxNumber;

    @Column(length = 255, nullable = false)
    private String contactNumber;

    @Column(length = 255, nullable = false)
    private String alternateContact;

    @Column(length = 255, nullable = true)
    private String addressOne;


    @Column(length = 255, nullable = true)
    private String addressTwo;

    @Column(length = 255, nullable = true)
    private String city;

    @Column(length = 255, nullable = true)
    private String state;

    @Column(length = 255, nullable = true)
    private String country;

    @Column(length = 255, nullable = true)
    private Long zipCode;

    @Column(length = 255, nullable = true)
    private String websiteUrl;

    @Transient
    private Boolean enable;

    @Transient
    private String isUserActive;

    @Transient
    private String owner;

    @Transient
    private String note;

    @Transient
    private String engagementModelOther;

    @Transient
    private String industryOther;


    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany( cascade = CascadeType.REMOVE)
    @JoinTable( name="client_details_contact",
            joinColumns=@JoinColumn(name="id"))
    private Collection<ClientContacts> clientContacts = new HashSet<ClientContacts>();


    @ManyToMany(mappedBy = "clientDetails"
            , targetEntity = Positions.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @org.hibernate.annotations.Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    private Set<Positions> clientPositions=new HashSet<Positions>();


    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "clientDetails",cascade = CascadeType.REMOVE)
    private Collection<Positions> positions = new HashSet<Positions>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getEngagementModel() {
        return engagementModel;
    }

    public void setEngagementModel(String engagementModel) {
        this.engagementModel = engagementModel;
    }

    public String getFederalId() {
        return federalId;
    }

    public void setFederalId(String federalId) {
        this.federalId = federalId;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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

    public Long getZipCode() {
        return zipCode;
    }

    public void setZipCode(Long zipCode) {
        this.zipCode = zipCode;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getIsUserActive() {
        return isUserActive;
    }

    public void setIsUserActive(String isUserActive) {
        this.isUserActive = isUserActive;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEngagementModelOther() {
        return engagementModelOther;
    }

    public void setEngagementModelOther(String engagementModelOther) {
        this.engagementModelOther = engagementModelOther;
    }

    public String getIndustryOther() {
        return industryOther;
    }

    public void setIndustryOther(String industryOther) {
        this.industryOther = industryOther;
    }

    public Collection<ClientContacts> getClientContacts() {
        return clientContacts;
    }

    public void setClientContacts(Collection<ClientContacts> clientContacts) {
        this.clientContacts = clientContacts;
    }

    public Set<Positions> getClientPositions() {
        return clientPositions;
    }

    public void setClientPositions(Set<Positions> clientPositions) {
        this.clientPositions = clientPositions;
    }

    public Collection<Positions> getPositions() {
        return positions;
    }

    public void setPositions(Collection<Positions> positions) {
        this.positions = positions;
    }
}
