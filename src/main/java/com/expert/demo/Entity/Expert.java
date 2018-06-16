package com.expert.demo.Entity;

import javax.persistence.*;

//专家类
@Entity
public class Expert
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer expertId;

    //专家姓名
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "TEXT")
    private String name;

    //专家专业领域
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "TEXT")
    private String field;

    //专家所属机构
    private String institution;

    //专家科研项目
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "TEXT")
    private String project;

    //专家授权专利
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "TEXT")
    private String patent;

    //专家其他成就
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "TEXT")
    private String otherAchievement;

    //专家个人介绍
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "TEXT")
    private String introducation;

    //专家对于自己门户的管理权
    private Boolean isAuthenticated;

    private Integer tradingNumber;

    //外键，代表专家对应的用户
    @ManyToOne
    @JoinColumn(name = "user_id",foreignKey = @ForeignKey(name = "EXPERT_USER_ID_FK"))
    private User user;

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

    public Boolean getAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTradingNumber() {
        return tradingNumber;
    }

    public void setTradingNumber(Integer tradingNumber) {
        this.tradingNumber = tradingNumber;
    }
}
