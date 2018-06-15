package com.expert.demo.AssitClass;

import com.expert.demo.Entity.Expert;
import com.expert.demo.Entity.Paper;
import com.expert.demo.Repository.PaperRepository;

import java.util.ArrayList;
import java.util.List;

public class CustomizedExpert
{
    private Integer expertId;

    private String name;

    private String field;

    private String patent;

    private List<CustomizedPaper> paperList;

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

    public List<CustomizedPaper> getPaperList() {
        return paperList;
    }

    public void setPaperList(List<CustomizedPaper> paperList) {
        this.paperList = paperList;
    }

    public CustomizedExpert(Expert expert, PaperRepository paperRepository)
    {
        this.expertId=expert.getExpertId();
        this.name=expert.getUser().getName();
        this.field=expert.getField();
        this.paperList=new ArrayList<>();
        List<Paper> papers=paperRepository.findPapersByExpert(expert);
        for( int i=0;i<papers.size();i++)
        {
            paperList.add(new CustomizedPaper(papers.get(i)));
        }
        this.patent=expert.getPatent();
    }
}
