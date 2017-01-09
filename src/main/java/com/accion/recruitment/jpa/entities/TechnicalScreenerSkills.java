package com.accion.recruitment.jpa.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/28/16 00:11 AM#$
 */
@Entity
@Table(name = "technicalscreener_skills")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class TechnicalScreenerSkills extends BaseEntity {

    public TechnicalScreenerSkills() {
    }

    public TechnicalScreenerSkills(String primarySkills, String primarySkillsYears, String secondarySkills, String primarySkillsMonths, String secondarySkillsYears, String secondarySkillsMonths) {
        this.primarySkills = primarySkills;
        this.primarySkillsYears = primarySkillsYears;
        this.secondarySkills = secondarySkills;
        this.primarySkillsMonths = primarySkillsMonths;
        this.secondarySkillsYears = secondarySkillsYears;
        this.secondarySkillsMonths = secondarySkillsMonths;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id" ,insertable = true, nullable = false, unique = true, updatable = true)
    protected Integer id;

    @Transient
    private String primarySkills;
    @Transient
    private String primarySkillsYears;
    @Transient
    private String primarySkillsMonths;

    @Transient
    private String secondarySkills;
    @Transient
    private String secondarySkillsYears;
    @Transient
    private String secondarySkillsMonths;
    @Transient
    private String tsName;

    private String skillType;

    private String skills;

    private Long years;

    private Long months;


    @ManyToMany(mappedBy="technicalScreenerDetailsDSkillsSet"
            , targetEntity = User.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
    private Collection<User> technicalScreenerDetailsSet=new HashSet<User>();


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

    public String getPrimarySkillsYears() {
        return primarySkillsYears;
    }

    public void setPrimarySkillsYears(String primarySkillsYears) {
        this.primarySkillsYears = primarySkillsYears;
    }

    public String getPrimarySkillsMonths() {
        return primarySkillsMonths;
    }

    public void setPrimarySkillsMonths(String primarySkillsMonths) {
        this.primarySkillsMonths = primarySkillsMonths;
    }

    public String getSecondarySkillsYears() {
        return secondarySkillsYears;
    }

    public void setSecondarySkillsYears(String secondarySkillsYears) {
        this.secondarySkillsYears = secondarySkillsYears;
    }

    public String getSecondarySkillsMonths() {
        return secondarySkillsMonths;
    }

    public void setSecondarySkillsMonths(String secondarySkillsMonths) {
        this.secondarySkillsMonths = secondarySkillsMonths;
    }

    public String getTsName() {
        return tsName;
    }

    public void setTsName(String tsName) {
        this.tsName = tsName;
    }

    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Long getYears() {
        return years;
    }

    public void setYears(Long years) {
        this.years = years;
    }

    public Long getMonths() {
        return months;
    }

    public void setMonths(Long months) {
        this.months = months;
    }

    public Collection<User> getTechnicalScreenerDetailsSet() {
        return technicalScreenerDetailsSet;
    }

    public void setTechnicalScreenerDetailsSet(Collection<User> technicalScreenerDetailsSet) {
        this.technicalScreenerDetailsSet = technicalScreenerDetailsSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TechnicalScreeenerSkills [skillType=" + skillType + ", skills=" + skills + ",years=" + years + "]";
    }

}
