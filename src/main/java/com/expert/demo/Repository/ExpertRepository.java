package com.expert.demo.Repository;

import com.expert.demo.Entity.Expert;
import com.expert.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpertRepository extends JpaRepository<Expert,Integer>
{
        public Expert getByExpertId(int expertId);

        @Query("select COUNT(u) from Expert u where u.user=?1")
        public int getNumberOfExpertByUser(User user);

        public List<Expert> findExpertsByUser(User user);

        public List<Expert> findExpertsByFieldLike(String field);

        public List<Expert> findExpertsByProjectLike(String project);

        public List<Expert> findExpertsByInstitutionLike(String institution);

        public Expert findExpertByUser(User user);
}
