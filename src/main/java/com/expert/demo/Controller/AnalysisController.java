package com.expert.demo.Controller;

import com.expert.demo.Entity.Achievement;
import com.expert.demo.Entity.Expert;
import com.expert.demo.Repository.ExpertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.expert.demo.Repository.AchievementRepository;

import java.util.List;

@CrossOrigin
@RestController
public class AnalysisController {
    @Autowired
    AchievementRepository achievementRepository;
    @Autowired
    ExpertRepository expertRepository;

    @GetMapping(value = "/analysis/popularAchievement")
    public List<Achievement> getTopAchievement()
    {
        return achievementRepository.getTop100Achievemnt();
    }

    @GetMapping(value="/analysis/popularExpert")
    public List<Expert> getTopExpert()
    {
        return  expertRepository.getTop100Expert();
    }

    @GetMapping(value="/analysis/popularInstitution")
    public List<String> getTopInstitution()
    {
        return expertRepository.getTop100Institution();
    }
}
