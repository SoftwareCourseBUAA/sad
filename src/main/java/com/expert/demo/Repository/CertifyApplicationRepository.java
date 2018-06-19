package com.expert.demo.Repository;

import com.expert.demo.Entity.CertifyApplication;
import com.expert.demo.Entity.MatchApplication;
import com.expert.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CertifyApplicationRepository extends JpaRepository<CertifyApplication,Integer> {
   public CertifyApplication findByUser(User user);

   public CertifyApplication findByApplyId(int appid);

   @Query(value="select count(a) from CertifyApplication a where a.applyId>0")
   public int getAppNumber();
}
