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

}
