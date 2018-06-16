package com.expert.demo.Controller;


import com.expert.demo.AssitClass.CustomizedAchievement;
import com.expert.demo.AssitClass.CustomizedExpert;
import com.expert.demo.Entity.Achievement;
import com.expert.demo.Entity.Expert;
import com.expert.demo.Entity.ExpertAndPaper;
import com.expert.demo.Entity.User;
import com.expert.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class SearchController
{

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpertAndPaperRepository expertAndPaperRepository;

    //查找相似专家姓名的专家信息
    @GetMapping(value = "/search/expert/name")
    public List<CustomizedExpert> getExpertsByName(@RequestParam("name") String name)
    {
        List<CustomizedExpert> customizedExpertList=new ArrayList<>();
        if( name!=""&&name!=null )
        {
            List<User> userList = userRepository.findUsersByNameContaining(name);
            System.out.println(userList.size());
            for (int i = 0; i < userList.size(); i++)
            {
                List<Expert> expertList=expertRepository.findExpertsByUser(userList.get(i));
                for (int j = 0; j < expertList.size(); j++)
                {
                    customizedExpertList.add(new CustomizedExpert(expertList.get(j),expertAndPaperRepository));
                }
            }
        }
        return customizedExpertList;
    }

    //查找相似领域的专家信息
    @GetMapping(value = "/search/expert/field")
    public List<CustomizedExpert> getExpertsByField(@RequestParam("field") String field)
    {
        List<CustomizedExpert> customizedExpertList=new ArrayList<>();
        if( field!=null&&field!="")
        {
            List<Expert> expertList = expertRepository.findExpertsByFieldContaining(field);
            for (int i = 0; i < expertList.size(); i++)
            {
                customizedExpertList.add(new CustomizedExpert(expertList.get(i),expertAndPaperRepository));
            }
        }
        return customizedExpertList;
    }

    //查找相似项目的专家信息
    @GetMapping(value = "/search/expert/project")
    public List<CustomizedExpert> getExpertsByProject(@RequestParam("project") String project)
    {
        List<CustomizedExpert> customizedExpertList=new ArrayList<>();
        if( project!=null&&project!="")
        {
            List<Expert> expertList = expertRepository.findExpertsByProjectContaining(project);
            for (int i = 0; i < expertList.size(); i++)
            {
                customizedExpertList.add(new CustomizedExpert(expertList.get(i),expertAndPaperRepository));
            }
        }
        return customizedExpertList;
    }

    //查找相似机构的专家信息
    @GetMapping(value = "/search/expert/institution")
    public List<CustomizedExpert> getExpertsByInstitution(@RequestParam("institution") String institution)
    {
        List<CustomizedExpert> customizedExpertList=new ArrayList<>();
        if( institution!=null&&institution!="")
        {
            List<Expert> expertList = expertRepository.findExpertsByInstitutionContaining(institution);
            for (int i = 0; i < expertList.size(); i++)
            {
                customizedExpertList.add(new CustomizedExpert(expertList.get(i),expertAndPaperRepository));
            }
        }
        return customizedExpertList;
    }

    //查找相似命名的成果信息
    @GetMapping(value = "/search/achievement")
    public List<CustomizedAchievement> getAchievementsByName(@RequestParam("name") String name,
                                                             @RequestParam(value = "page",defaultValue = "-1") int page,
                                                             @RequestParam(value = "size",defaultValue = "-1") int size)
    {
        List<CustomizedAchievement> customizedAchievementList=new ArrayList<>();
        if( name!=null&&name!="" )
        {
            List<Achievement> achievementList;
            if(page==-1||size==-1) {
                achievementList = achievementRepository.getAchievementsByAchievementNameContaining(name);
            }
            else {
                Pageable pageable=new PageRequest(page,size,Sort.Direction.ASC,"achievementId");
                achievementList = achievementRepository.getAchievementsByAchievementNameContaining(name,pageable).getContent();
            }
            for( int i=0;i<achievementList.size();i++ )
            {
                customizedAchievementList.add(new CustomizedAchievement(achievementList.get(i)));
            }
        }
        return customizedAchievementList;
    }
}
