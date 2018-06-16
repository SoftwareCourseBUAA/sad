package com.expert.demo.Repository;

import com.expert.demo.Entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationRepository extends JpaRepository<Application,Integer>{
    public Application findByApplyId(int applyId);

    @Query("select  count(a) from Application a where a.applyId>0")
    public int getAppNumber();

}
