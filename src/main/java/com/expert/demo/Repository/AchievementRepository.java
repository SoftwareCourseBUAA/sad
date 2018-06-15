package com.expert.demo.Repository;

import com.expert.demo.Entity.Achievement;
import com.expert.demo.Entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchievementRepository extends JpaRepository<Achievement,Integer>
{
    public List<Achievement> getAchievementsByExpert(Expert expert);

    public List<Achievement> getAchievementsByAchievementNameContaining(String name);

    public Achievement getAchievementByAchievementId( int achievementId);

    public Achievement findAchievementByAchievementId(int achievementId);



}
