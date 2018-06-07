package com.expert.demo.Entity;

import javax.persistence.*;

@Entity
public class Trading
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tradingId;

    @ManyToOne
    @JoinColumn(name="achievemnet_id")
    private Achievement achievement;

    @ManyToOne
    @JoinColumn(name="user_id")
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
