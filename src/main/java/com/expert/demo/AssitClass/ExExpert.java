package com.expert.demo.AssitClass;

import com.expert.demo.Entity.Expert;

public class ExExpert
{

    public ExExpert(){

    }

    public ExExpert(Expert e) {
        this.expertId=(e.getExpertId());
        this.patent=(e.getPatent());
        this.project=(e.getProject());
        this.paper=(e.getPaper());
        this.institution=(e.getInstitution());
        this.introducation=(e.getIntroducation());
        this.otherAchievement=(e.getOtherAchievement());
        this.field=(e.getField());
        this.userId=e.getUser().getUserId();
    }

    private Integer expertId;

    //专家专业领域
    private String field;

    //专家所属机构
    private String institution;

    //专家科研项目
    private String project;

    //专家科研论文
    private String paper;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //专家名字
    private String name;

    //专家授权专利
    private String patent;

    //专家其他成就
    private String otherAchievement;

    //专家个人介绍
    private String introducation;

    //外键，代表专家关联的用户
    private Integer userId;

    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public String getPatent() {
        return patent;
    }

    public void setPatent(String patent) {
        this.patent = patent;
    }

    public String getOtherAchievement() {
        return otherAchievement;
    }

    public void setOtherAchievement(String otherAchievement) {
        this.otherAchievement = otherAchievement;
    }

    public String getIntroducation() {
        return introducation;
    }

    public void setIntroducation(String introducation) {
        this.introducation = introducation;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
