package com.accion.recruitment.jpa.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 20/01/17 00:11 AM#$
 */

@Entity
@Table(name = "general_question")

public class GeneralQuestion extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id" ,insertable = true, nullable = false, unique = true, updatable = true)
    protected Integer id;

    @Column(length=1000)
    private String questionName;
    @Column(length=500)
    private  String option1;
    @Column(length=500)
    private  String option2;
    @Column(length=500)
    private  String option3;
    @Column(length=500)
    private  String option4;
    @Column(length=500)
    private  String option5;
    @Column(length=500)
    private  String option6;

    private  String answer;

    private String addToPosition;




    @ManyToMany(targetEntity = Positions.class , fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(name = "position_general_question",
            joinColumns = @JoinColumn(name = "general_question_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id"))
    private Set<Positions> generalQuestionPositionsSet=new HashSet<Positions>();

    public Set<Positions> getGeneralQuestionPositionsSet() {
        return generalQuestionPositionsSet;
    }

    public void setGeneralQuestionPositionsSet(Set<Positions> generalQuestionPositionsSet) {
        this.generalQuestionPositionsSet = generalQuestionPositionsSet;
    }

    public void setPositions(Positions positions) {
        this.generalQuestionPositionsSet.add(positions) ;
    }

    @Transient
    private String subAnswer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubAnswer() {
        return subAnswer;
    }

    public void setSubAnswer(String subAnswer) {
        this.subAnswer = subAnswer;
    }

    public String getQuestionName() {
        return questionName;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getOption5() {
        return option5;
    }

    public String getOption6() {
        return option6;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    public void setOption6(String option6) {
        this.option6 = option6;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAddToPosition() {
        return addToPosition;
    }

    public void setAddToPosition(String addToPosition) {
        this.addToPosition = addToPosition;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        } else if (!(o instanceof GeneralQuestion )) {
            return false;
        } else {
            return ((GeneralQuestion) o).getId().equals(this.id) ;
        }
    }
}
