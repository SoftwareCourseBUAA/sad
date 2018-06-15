package com.expert.demo.Controller;

import com.expert.demo.AssitClass.ExAchievement;
import com.expert.demo.Entity.Achievement;
import com.expert.demo.Entity.Expert;
import com.expert.demo.Repository.AchievementRepository;
import com.expert.demo.Repository.ExpertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class AchievementController
{

    @Autowired
    AchievementRepository achievementRepository;

    @Autowired
    ExpertRepository expertRepository;



    @PostMapping(value = "/achievement")
    public Boolean addAchievement(@RequestBody ExAchievement exAchievement)
    {
        Achievement achievement=new Achievement();
        Expert expert=expertRepository.getByExpertId(exAchievement.getExpertId());
        if( expert!=null )
        {
            achievement.setExpert(expert);
            if( exAchievement.getIntroduction()!=null )
                achievement.setIntroduction(exAchievement.getIntroduction());
            if( exAchievement.getType()!=null )
                achievement.setType(exAchievement.getType());
            else
            {
                return false;
            }
            if(exAchievement.getAchievementName()!=null )
            {
                achievement.setAchievementName(exAchievement.getAchievementName());
            }
            else
            {
                return false;
            }
            if( exAchievement.getPoint()!=null )
            {
                achievement.setPoint(exAchievement.getPoint());
            }
            else
            {
                achievement.setPoint(0);
            }
            if( exAchievement.getDownloadUrl()!=null )
            {
                achievement.setDownloadUrl(exAchievement.getDownloadUrl());
            }
            else
            {
                achievement.setDownloadUrl("");
            }
            achievementRepository.save(achievement);
            return true;
        }
        else
        {
            return false;
        }
    }


    @GetMapping(value = "/achievement/expert/{expertId}")
    public List<Achievement> getAchievementByExpert( @PathVariable("expertId") int expertId)
    {
        Expert expert=expertRepository.getByExpertId(expertId);
        List<Achievement> achievementList=new ArrayList<>();
        if( expert!=null )
        {
            achievementList=achievementRepository.getAchievementsByExpert(expert);
            for( int i=0;i<achievementList.size();i++ )
            {
                achievementList.get(i).getExpert().getUser().setPassword("");
            }
        }
        return achievementList;
    }
}
