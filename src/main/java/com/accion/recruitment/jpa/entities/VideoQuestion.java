package com.accion.recruitment.jpa.entities;


import com.accion.recruitment.beans.QuestionBaseClass;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 20/01/17 00:11 AM#$
 */

@Entity
@Table(name = "video_question")

public class VideoQuestion extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id" ,insertable = true, nullable = false, unique = true, updatable = true)
    protected Integer id;

    @Column(length=500)
    private String domain;

    @Column(length=500)
    private String level;

    @Column(length=1000)
    private String questionName;

    @Transient
    private String otherDomain;

    @ManyToMany(targetEntity = Positions.class , fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(name = "position_video_question",
            joinColumns = @JoinColumn(name = "video_question_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id"))
    private Set<Positions> videoQuestionPositionsSet=new HashSet<Positions>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getOtherDomain() {
        return otherDomain;
    }

    public void setOtherDomain(String otherDomain) {
        this.otherDomain = otherDomain;
    }

    public Set<Positions> getVideoQuestionPositionsSet() {
        return videoQuestionPositionsSet;
    }

    public void setVideoQuestionPositionsSet(Set<Positions> videoQuestionPositionsSet) {
        this.videoQuestionPositionsSet = videoQuestionPositionsSet;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        } else if (!(o instanceof VideoQuestion )) {
            return false;
        } else {
            return ((VideoQuestion) o).getId().equals(this.id) ;
        }
    }
}
