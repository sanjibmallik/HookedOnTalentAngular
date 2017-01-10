package com.accion.recruitment.jpa.entities;


import javax.persistence.*;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 09/01/17 00:11 AM#$
 */

@Entity
@Table(name = "engagement_model")
public class EngagementModel  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id" ,insertable = true, nullable = false, unique = true, updatable = true)
    protected Integer id;

    @Column(length = 255, nullable = false)
    private String engagementModel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEngagementModel() {
        return engagementModel;
    }

    public void setEngagementModel(String engagementModel) {
        this.engagementModel = engagementModel;
    }
}
