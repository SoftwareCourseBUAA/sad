package com.expert.demo.Repository;

import com.expert.demo.Entity.Expert;
import com.expert.demo.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpertRepository extends JpaRepository<Expert,Integer>
{
        public Expert getByExpertId(int expertId);

        public List<Expert> getByUser(User user);

        public Expert getByName(String name);

        @Query("select COUNT(u) from Expert u where u.user=?1")
        public int getNumberOfExpertByUser(User user);

        public Page<Expert> findExpertsByNameContaining(String name , Pageable pageable);
        @Query("select  COUNT(e) from Expert e where e.expertId>0")
        public int getNumberOfExpert();

        public List<Expert> findExpertsByUser(User user);

        public Page<Expert> findExpertsByFieldContaining(String field,Pageable pageable);

        public Page<Expert> findExpertsByProjectContaining(String project,Pageable pageable);

        public Page<Expert> findExpertsByInstitutionContaining(String institution,Pageable pageable);

        public Expert findExpertByUser(User user);

        @Query(value = "select * from expert order by trading_number desc limit 100",nativeQuery = true)
        public List<Expert> getTop100Expert();

        @Query(nativeQuery = true,value = "select institution from expert group by institution " +
                "order by sum(trading_number) desc limit 100")
        public List<String> getTop100Institution();

}
