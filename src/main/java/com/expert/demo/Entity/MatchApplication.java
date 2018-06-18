package com.expert.demo.Entity;

import javax.persistence.*;

//认领已经有的专家用户的类
@Entity
public class MatchApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applyId;

    //证明材料的下载url
    private String downloadUrl;


    @OneToOne
    @JoinColumn(name="user_id")

    private User user;

    @OneToOne
    @JoinColumn(name="expert_id")
    private Expert expert;

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
