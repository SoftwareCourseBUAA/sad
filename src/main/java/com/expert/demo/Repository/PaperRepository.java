package com.expert.demo.Repository;

import com.expert.demo.Entity.Expert;
import com.expert.demo.Entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaperRepository extends JpaRepository<Paper,Integer>
{
    public Paper getByPaperId(int paperId);

    @Query(value="select count(p) from Paper p where p.paperId>0")
    public int getPaperCount();
}
