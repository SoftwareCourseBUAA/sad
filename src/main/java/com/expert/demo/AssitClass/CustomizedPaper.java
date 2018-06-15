package com.expert.demo.AssitClass;

import com.expert.demo.Entity.Paper;

public class CustomizedPaper
{
    private String title;

    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CustomizedPaper(Paper paper)
    {
        this.title=paper.getTitle();
        this.url=paper.getUrl();
    }
}
