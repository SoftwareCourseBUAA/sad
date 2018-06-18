package com.expert.demo.AssitClass;

import com.expert.demo.Entity.Trading;

public class CustomizedTrading
{
    private String sellerNickname;

    private String buyerNickname;

    private int point;

    private String achievementName;

    private String Introduction;

    public String getSellerNickname() {
        return sellerNickname;
    }

    public void setSellerNickname(String sellerNickname) {
        this.sellerNickname = sellerNickname;
    }

    public String getBuyerNickname() {
        return buyerNickname;
    }

    public void setBuyerNickname(String buyerNickname) {
        this.buyerNickname = buyerNickname;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }

    public CustomizedTrading( Trading trading )
    {
        this.achievementName=trading.getAchievement().getAchievementName();
        this.buyerNickname=trading.getUser().getNickname();
        this.sellerNickname=trading.getAchievement().getExpert().getUser().getNickname();
        this.Introduction=trading.getAchievement().getIntroduction();
        this.point=trading.getAchievement().getPoint();
    }
}
