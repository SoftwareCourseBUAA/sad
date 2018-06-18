package com.expert.demo.Repository;

import com.expert.demo.Entity.MatchApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MatchApplicationRepository extends JpaRepository<MatchApplication,Integer>{
    public MatchApplication findByApplyId(int applyId);

    @Query("select  count(a) from MatchApplication a where a.applyId>0")
    public int getAppNumber();

}
