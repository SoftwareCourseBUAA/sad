package com.expert.demo.Repository;

import com.expert.demo.Entity.Expert;
import com.expert.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpertRepository extends JpaRepository<Expert,Integer>
{
        public Expert getByExpertId(int expertId);

        public Expert getByUser(User user);

        public Expert getByName(String name);

        @Query("select COUNT(u) from Expert u where u.user=?1")
        public int getNumberOfExpertByUser(User user);

        public List<Expert> findExpertsByUser(User user);

        public List<Expert> findExpertsByFieldContaining(String field);

        public List<Expert> findExpertsByProjectContaining(String project);

        public List<Expert> findExpertsByInstitutionContaining(String institution);

        public Expert findExpertByUser(User user);
}
