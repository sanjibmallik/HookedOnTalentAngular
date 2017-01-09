package com.accion.recruitment.jpa.entities;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 09/01/17 00:11 AM#$
 */
public class UserSkills {
    private User user;
    private TechnicalScreenerSkills technicalScreenerSkills;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TechnicalScreenerSkills getTechnicalScreenerSkills() {
        return technicalScreenerSkills;
    }

    public void setTechnicalScreenerSkills(TechnicalScreenerSkills technicalScreenerSkills) {
        this.technicalScreenerSkills = technicalScreenerSkills;
    }
}
