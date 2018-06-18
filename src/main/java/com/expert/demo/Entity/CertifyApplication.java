package com.expert.demo.Entity;

import javax.persistence.*;

//申请成为专家用户的类
@Entity
public class CertifyApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applyId;

    //申请时的专家信息
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

    //申请证明材料
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "TEXT")
    private String downloadUrl;

    @OneToOne
    @JoinColumn(name="user_id")
    private  User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
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

    public String getinstitution() {
        return institution;
    }

    public void setinstitution(String institution) {
        institution = institution;
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

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
