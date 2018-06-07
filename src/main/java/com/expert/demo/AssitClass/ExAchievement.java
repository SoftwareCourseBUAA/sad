package com.expert.demo.AssitClass;

public class ExAchievement
{
    private Integer achievementId;

    //成果介绍
    private String introduction;

    //成果命名
    private String achievementName;

    //交易类型，分为积分交易，设置为1 和联系专家，设置为2 两种
    private Integer type;

    //专家资源花费积分
    private Integer point;

    //专家资源下载链接
    private String downloadUrl;

    //成果所属专家

    private Integer expertId;

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

    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }
}
