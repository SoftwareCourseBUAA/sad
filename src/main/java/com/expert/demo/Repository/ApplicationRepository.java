package com.expert.demo.Repository;

import com.expert.demo.Entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application,Integer>{
    public Application findByApplyId(int applyId);

}
