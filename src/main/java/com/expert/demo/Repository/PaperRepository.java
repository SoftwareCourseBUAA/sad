package com.expert.demo.Repository;

import com.expert.demo.Entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<Paper,Integer> {
}
