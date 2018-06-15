package com.expert.demo.Entity;

import javax.persistence.*;

@Entity
public class Paper
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paperId;

    private String title;

    private String url;

    @ManyToOne
    @JoinColumn(name = "expert_id",foreignKey = @ForeignKey(name = "PAPER_EXPERT_ID_FK"))
    private Expert expert;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }
}
