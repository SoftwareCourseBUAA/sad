package com.expert.demo.AssitClass;

import com.expert.demo.Entity.Expert;
import com.expert.demo.Entity.User;

public class CustomizedExpertForAdmin {

    private int expertid;

    private String name;

    public int getExpertid() {
        return expertid;
    }

    public void setExpertid(int expertid) {
        this.expertid = expertid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    public CustomizedExpertForAdmin(Expert e)
    {
        this.expertid = e.getExpertId();
        this.name = e.getName();
        if(e.getUser()!=null)
        {
            this.user = e.getUser();
        }
        else{
            this.user=null;
        }
    }
}
