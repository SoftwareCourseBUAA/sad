package com.expert.demo.Repository;

import com.expert.demo.Entity.CertifyApplication;
import com.expert.demo.Entity.MatchApplication;
import com.expert.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertifyApplicationRepository extends JpaRepository<CertifyApplication,Integer> {
   public CertifyApplication findByUser(User user);
}
