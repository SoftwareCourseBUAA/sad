package com.expert.demo.Entity;

import javax.persistence.*;

@Entity
public class Administor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer administorId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getAdministorId() {
        return administorId;
    }

    public void setAdministorId(Integer administorId) {
        this.administorId = administorId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
