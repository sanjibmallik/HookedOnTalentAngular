package com.accion.recruitment.jpa.entities;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 27/01/17 00:11 AM#$
 */


@Entity
@Table(name = "domain")

public class Domain extends BaseEntity {


    private String domain;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
