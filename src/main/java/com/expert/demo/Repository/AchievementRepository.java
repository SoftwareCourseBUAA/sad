package com.expert.demo.Repository;

import com.expert.demo.Entity.Achievement;
import com.expert.demo.Entity.Expert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AchievementRepository extends JpaRepository<Achievement,Integer>
{
    @Query (value="select * from achievement  order by download_number desc limit 100",nativeQuery = true)
    public List<Achievement>getTop100Achievement();

    public List<Achievement> getAchievementsByExpert(Expert expert);

    public Page<Achievement> getAchievementsByExpert(Expert expert, Pageable pageable);

    public List<Achievement> getAchievementsByAchievementNameContaining(String name);

    public Page<Achievement> getAchievementsByAchievementNameContaining(String name,Pageable pageable);

    public Achievement getAchievementByAchievementId( int achievementId);

    public Achievement findAchievementByAchievementId(int achievementId);



}
