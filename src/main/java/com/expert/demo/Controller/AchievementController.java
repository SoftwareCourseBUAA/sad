package com.expert.demo.Controller;

import com.expert.demo.AssitClass.ExAchievement;
import com.expert.demo.Entity.Achievement;
import com.expert.demo.Entity.Expert;
import com.expert.demo.Repository.AchievementRepository;
import com.expert.demo.Repository.ExpertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class AchievementController
{
    @Autowired
    ExpertRepository expertRepository;

    @Autowired
    AchievementRepository achievementRepository;

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
            String downloadUrl="F:/upload/"+exAchievement.getAchievementName();
            achievement.setDownloadUrl(downloadUrl);
            achievementRepository.save(achievement);
            return true;
        }
        else
        {
            return false;
        }
    }
    @PostMapping(value="achievement/update")
    public  Achievement updateAchievement(@RequestBody ExAchievement exAchievement)
    {
        Achievement achievement=achievementRepository.findAchievementByAchievementId(exAchievement.getAchievementId());
        if(achievement!=null)
        {
            if(!exAchievement.getIntroduction().equals(""))
                achievement.setIntroduction(exAchievement.getIntroduction());
            if( exAchievement.getType()!=null )
                achievement.setType(exAchievement.getType());
            if(!exAchievement.getAchievementName().equals(""))
            {
                achievement.setAchievementName(exAchievement.getAchievementName());
            }
            if( exAchievement.getPoint()!=null )
            {
                achievement.setPoint(exAchievement.getPoint());
            }
            return achievementRepository.save(achievement);
        }
        else
        {
            Achievement achievement1=new Achievement();
            achievement1.setAchievementId(exAchievement.getAchievementId());
            return achievement1;
        }
    }
    @PostMapping(value = "achievement/upload")
    public String upLoadAchievement(@RequestParam("file") MultipartFile file,HttpServletRequest request)
    {
        if (!file.isEmpty()) {
            String saveFileName = file.getOriginalFilename();
            String pathName="F:/upload/"+saveFileName;
            File saveFile = new File(pathName);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                return pathName;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "";
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }
    @GetMapping(value = "achievement/delete")
    public Boolean deleteAchievement(@RequestParam("achievementId") int achievementId)
    {
        try {
            achievementRepository.deleteById(achievementId);
            return true;
        }
        catch (Exception e){
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
