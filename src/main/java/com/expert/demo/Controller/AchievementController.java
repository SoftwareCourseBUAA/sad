package com.expert.demo.Controller;

import com.expert.demo.AssitClass.ExAchievement;
import com.expert.demo.Entity.Achievement;
import com.expert.demo.Entity.Expert;
import com.expert.demo.Repository.AchievementRepository;
import com.expert.demo.Repository.ExpertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
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
    public int addAchievement(@RequestBody ExAchievement exAchievement)
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
                return -1;
            }
            if(exAchievement.getAchievementName()!=null )
            {
                achievement.setAchievementName(exAchievement.getAchievementName());
            }
            else
            {
                return -1;
            }
            if( exAchievement.getPoint()!=null )
            {
                achievement.setPoint(exAchievement.getPoint());
            }
            else
            {
                achievement.setPoint(0);
            }
            String downloadUrl="upload/"+exAchievement.getAchievementName();
            achievement.setDownloadUrl(downloadUrl);
            achievement.setDownloadNumber(0);
            achievementRepository.save(achievement);
            return achievement.getAchievementId();
        }
        else
        {
            return -1;
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
    public Boolean upLoadAchievement(@RequestParam("file") MultipartFile file
            ,@RequestParam("achievementId") int achievementId)
    {
        if(achievementId==-1)
            return false;
        if (!file.isEmpty()) {
            String saveFileName = file.getOriginalFilename();
            String pathName = "upload/" + saveFileName;
            File saveFile = new File(pathName);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                String savePath=saveFile.getAbsolutePath();
                Achievement achievement=achievementRepository.findAchievementByAchievementId(achievementId);
                achievement.setDownloadUrl(savePath);
                achievementRepository.save(achievement);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
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
    public List<Achievement> getAchievementByExpert( @PathVariable("expertId") int expertId,
                                                     @RequestParam(value = "page",defaultValue = "-1") int page,
                                                     @RequestParam(value = "size",defaultValue = "-1") int size)
    {
        Expert expert=expertRepository.getByExpertId(expertId);
        List<Achievement> achievementList=new ArrayList<>();
        if( expert!=null )
        {
            achievementList=achievementRepository.getAchievementsByExpert(expert);
            if( achievementList.size()>0 )
            {
                if( achievementList.get(0).getExpert().getUser()!=null )
                {
                    for (int i = 0; i < achievementList.size(); i++)
                    {
                        achievementList.get(i).getExpert().getUser().setPassword("");
                    }
                }
            }
        }
        else
            return null;
        if(page==-1||size==-1)
            return achievementList;
        else
        {
            Pageable pageable=new PageRequest(page,size,Sort.Direction.ASC,"achievementId");
            return achievementRepository.getAchievementsByExpert(expert,pageable).getContent();
        }

    }
}
