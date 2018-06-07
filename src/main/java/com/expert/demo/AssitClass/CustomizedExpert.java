package com.expert.demo.AssitClass;

import com.expert.demo.Entity.Expert;

public class CustomizedExpert
{
    private Integer expertId;

    private String name;

    private String field;

    private String patent;

    private String paper;

    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getPatent() {
        return patent;
    }

    public void setPatent(String patent) {
        this.patent = patent;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public CustomizedExpert(Expert expert)
    {
        this.expertId=expert.getExpertId();
        this.name=expert.getUser().getName();
        this.field=expert.getField();
        this.paper=expert.getPaper();
        this.patent=expert.getPatent();
    }
}
