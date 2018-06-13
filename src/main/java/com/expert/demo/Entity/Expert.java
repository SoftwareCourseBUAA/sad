package com.expert.demo.Entity;

import javax.persistence.*;

//专家类
@Entity
public class Expert
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer expertId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //专家姓名
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "TEXT")
    private String name;

    //专家专业领域
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "TEXT")
    private String field;

    //专家是否认证
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "BOOLEAN")
    private Boolean isAuthenticated;

    //专家所属机构
    private String institution;

    //专家科研项目
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "TEXT")
    private String project;

    //专家科研论文
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "TEXT")
    private String paper;

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


    @OneToOne
    @JoinColumn(name="user_id")
    //外键，代表专家关联的用户
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIntroducation() {
        return introducation;
    }

    public void setIntroducation(String introducation) {
        this.introducation = introducation;
    }

    public Boolean getIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(Boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }
}
