package com.expert.demo.AssitClass;


import com.expert.demo.Entity.User;

public class CustomizedUser
{
    private User user;

    private Integer identity;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public CustomizedUser() {
    }
}
