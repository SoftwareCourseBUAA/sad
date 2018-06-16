package com.expert.demo.Repository;

import com.expert.demo.Entity.Expert;
import com.expert.demo.Entity.ExpertAndPaper;
import com.expert.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpertAndPaperRepository extends JpaRepository<ExpertAndPaper,Integer>
{
    public List<ExpertAndPaper> findAllByExpert_User(User user);

    public List<ExpertAndPaper> findAllByExpert(Expert expert);
}
