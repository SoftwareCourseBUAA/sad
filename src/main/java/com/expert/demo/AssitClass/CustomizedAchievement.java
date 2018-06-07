package com.expert.demo.AssitClass;

import com.expert.demo.Entity.Achievement;

public class CustomizedAchievement
{
    private Integer achievementId;

    private String achievementName;

    private Integer type;

    private Integer point;

    private String introduction;

    private String name;

    public Integer getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Integer achievementId) {
        this.achievementId = achievementId;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomizedAchievement(Achievement achievement)
    {
        this.achievementId=achievement.getAchievementId();
        this.achievementName=achievement.getAchievementName();
        this.introduction=achievement.getIntroduction();
        this.point=achievement.getPoint();
        this.type=achievement.getType();
        this.name=achievement.getExpert().getUser().getName();
    }
}
