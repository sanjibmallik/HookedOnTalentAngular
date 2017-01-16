package com.accion.recruitment.jpa.entities;

/**
 * Created by AL1282 on 1/16/17.
 */
public class SecondarySkills {

    private String skills;
    private String months;
    private String years;

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
