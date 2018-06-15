package com.expert.demo.Entity;

import javax.persistence.*;

//专家成果类
@Entity
public class Achievement
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer achievementId;

    //成果的命名
    private String achievementName;

    //成果的简介
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "TEXT")
    private String introduction;

    //交易类型，分为积分交易，设置为1 和联系专家，设置为2 两种
    private Integer type;

    //专家资源花费积分
    private Integer point;

    //专家资源下载链接
    private String downloadUrl;

    private Integer downloadNumber;

    //成果所属专家
    @ManyToOne
    @JoinColumn(name="expert_id",foreignKey = @ForeignKey(name = "ACHIEVEMENT_EXPERT_ID_FK"))
    private Expert expert;

    public Integer getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Integer achievementId) {
        this.achievementId = achievementId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public Integer getDownloadNumber() {
        return downloadNumber;
    }

    public void setDownloadNumber(Integer downloadNumber) {
        this.downloadNumber = downloadNumber;
    }
}
