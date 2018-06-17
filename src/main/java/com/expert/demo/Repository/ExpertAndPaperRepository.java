package com.expert.demo.Repository;

import com.expert.demo.Entity.Expert;
import com.expert.demo.Entity.ExpertAndPaper;
import com.expert.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpertAndPaperRepository extends JpaRepository<ExpertAndPaper,Integer>
{
    public List<ExpertAndPaper> findAllByExpert_User(User user);

    public List<ExpertAndPaper> findAllByExpert(Expert expert);

    @Query(value="select count(ep) from ExpertAndPaper ep where ep.expert=?1")
    public  int getPaperCountByExpert(Expert e);

    @Query(value = "select * from ExpertAndPaper u where u.expertAndPaperId=?1",nativeQuery = true)
    public ExpertAndPaper findByExpertAndPaperId(int expertAndPaperId);


}
