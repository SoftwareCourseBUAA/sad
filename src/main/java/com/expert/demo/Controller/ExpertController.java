package com.expert.demo.Controller;

import com.expert.demo.AssitClass.CustomizedExpert;
import com.expert.demo.Entity.Expert;
import com.expert.demo.Entity.User;
import com.expert.demo.Repository.ExpertRepository;
import com.expert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import com.expert.demo.AssitClass.CustomizedExpertForAdmin;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class ExpertController
{
    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value="/expert/all")
    public List<CustomizedExpertForAdmin> getAllExperts(@RequestParam("page") int page, @RequestParam("size") int size)
    {
        List<CustomizedExpertForAdmin> customizedExpertList=new ArrayList<>();
        Pageable pageable=new PageRequest(page,size, Sort.Direction.ASC,"expertId");
        List<Expert> expertList=expertRepository.findAll(pageable).getContent();
        for (int j = 0; j < expertList.size(); j++)
        {
            customizedExpertList.add(new CustomizedExpertForAdmin(expertList.get(j)));
        }
        return customizedExpertList;
    }

    @GetMapping(value="/expert/count")
    public int getExpertsCount(){return expertRepository.getNumberOfExpert();}

    @GetMapping(value="/expert/e_id/{e_id}")
    public Expert getExpertByEId(@PathVariable("e_id") int eid)
    {
        Expert expert = expertRepository.getByExpertId(eid);
        return expert;
    }


    @GetMapping(value="/expert/name/{name}")
    public Expert getExpertInfoByName(@PathVariable("name") String name)
    {
        Expert expert=expertRepository.getByName(name);
        if(expert==null) {
            expert=new Expert();
            expert.setIntroducation("查无此人");
        }
        return expert;
    }

    @GetMapping(value="/expert/id/{userid}")
    public List<Expert> getExpertInfoByUserId(@PathVariable("userid") int userid)
    {
        User user = userRepository.findByUserId(userid);
        List<Expert> expert =new ArrayList<>();
        if(user!=null)
        {
            expert = expertRepository.getByUser(user);
            if(expert!=null)
            {
                return expert;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }

    @PutMapping(value="/expert/{expertid}")
    public Expert changeExpertInfo(@RequestBody Expert expert,@PathVariable("expertid") int expertid)
    {
        Expert expert1=expertRepository.getByExpertId(expertid);
        User user = expert1.getUser();
        if(expert1!=null)
        {
            if( expert.getField()!=null)
                expert1.setField(expert.getField());
            if( expert.getInstitution()!=null )
                expert1.setInstitution(expert.getInstitution());
            if( expert.getIntroducation()!=null)
                expert1.setIntroducation(expert.getIntroducation());
            if( expert.getOtherAchievement()!=null )
                expert1.setOtherAchievement(expert.getOtherAchievement());
            if(expert.getPatent()!=null)
                expert1.setPatent(expert.getPatent());
            if(expert.getProject()!=null)
                expert1.setProject(expert.getProject());
            if(expert.getTradingNumber()!=null)
                expert1.setTradingNumber(expert.getTradingNumber());
            if(expert.getName()!=null)
            {
                expert1.setName(expert.getName());
                user.setName(expert.getName());
            }
            if(expert.getUser().getEmail()!=null)
            {
                user.setEmail(expert.getUser().getEmail());
            }
            expertRepository.save(expert1);
            userRepository.save(user);
        }
        else{
            expert1=new Expert();
            expert1.setIntroducation("查无此人");
        }
        expert1.getUser().setPassword("");
        return expert1;
    }


    @PostMapping(value = "/expert")
    public Expert addExpert(@RequestBody Expert expert)
    {
        Expert expert1=new Expert();
        if(expert!=null && expert.getName()!=null&&expertRepository.getByName(expert.getName())==null)
        {
            expert1.setName(expert.getName());
            expert1.setTradingNumber(0);
            if(expert.getField()!=null)
            expert1.setField(expert.getField());
            if(expert.getIntroducation()!=null)
            expert1.setIntroducation(expert.getIntroducation());
            if(expert.getInstitution()!=null)
            expert1.setInstitution(expert.getInstitution());
            if(expert.getOtherAchievement()!=null)
            expert1.setOtherAchievement(expert.getOtherAchievement());
            if(expert.getProject()!=null)
            expert1.setProject(expert.getProject());
            if(expert.getPatent()!=null)
            expert1.setPatent(expert.getPatent());
            expert1.setTradingNumber(0);
            expertRepository.save(expert1);
            return expert1;
        }
        else if(expert!=null && expert.getName()!=null && expertRepository.getByName(expert.getName())!=null)
        {
            expert1.setIntroducation("无法添加重名专家");
            return expert1;
        }
        else{
            expert1.setIntroducation("关键信息缺失，无法添加专家");
            return expert1;
        }
    }
}
