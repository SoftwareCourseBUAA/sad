package com.expert.demo.Repository;

import com.expert.demo.Entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertRepository extends JpaRepository<Expert,Integer>
{
        public Expert getByExpertId(int expertId);
}
