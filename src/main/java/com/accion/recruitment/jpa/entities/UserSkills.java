package com.accion.recruitment.jpa.entities;

import com.sun.javafx.collections.MappingChange;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 09/01/17 00:11 AM#$
 */
public class UserSkills {
    private User user;
    /*private TechnicalScreenerSkills technicalScreenerSkills;*/
    private HashMap<String,Object> technicalScreenerSkills=new HashMap<String, Object>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HashMap<String, Object> getTechnicalScreenerSkills() {
        return technicalScreenerSkills;
    }

    public void setTechnicalScreenerSkills(HashMap<String, Object> technicalScreenerSkills) {
        this.technicalScreenerSkills = technicalScreenerSkills;
    }
}
