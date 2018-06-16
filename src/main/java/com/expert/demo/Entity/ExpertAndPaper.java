package com.expert.demo.Entity;

import javax.persistence.*;

@Entity
public class ExpertAndPaper
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer expertAndPaperId;

    @ManyToOne
    @JoinColumn(name="expert_id",foreignKey = @ForeignKey(name = "EXPERTANDUSER_EXPERT_ID_FK"))
    private Expert expert;

    @ManyToOne
    @JoinColumn(name="paper_id",foreignKey = @ForeignKey(name="EXPERTANDUSER_USER_ID_FK"))
    private Paper paper;

    public Integer getExpertAndPaperId() {
        return expertAndPaperId;
    }

    public void setExpertAndPaperId(Integer expertAndPaperId) {
        this.expertAndPaperId = expertAndPaperId;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }
}
