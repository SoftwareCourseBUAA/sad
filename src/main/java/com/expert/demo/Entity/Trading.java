package com.expert.demo.Entity;

import javax.persistence.*;

@Entity
public class Trading
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tradingId;

    @ManyToOne
    @JoinColumn(name="achievemnet_id",foreignKey = @ForeignKey(name = "TRADING_ACHIEVEMENT_ID_FK"))
    private Achievement achievement;

    @ManyToOne
    @JoinColumn(name="user_id",foreignKey = @ForeignKey(name = "TRADING_USER_ID_FK"))
    private User user;

    public Integer getTradingId() {
        return tradingId;
    }

    public void setTradingId(Integer tradingId) {
        this.tradingId = tradingId;
    }

    public Achievement getAchievement() {
        return achievement;
    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
