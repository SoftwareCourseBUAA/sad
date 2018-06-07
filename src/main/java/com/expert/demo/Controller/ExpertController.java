package com.expert.demo.Controller;

import com.expert.demo.AssitClass.ExExpert;
import com.expert.demo.Entity.Expert;
import com.expert.demo.Entity.User;
import com.expert.demo.Repository.ExpertRepository;
import com.expert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ExpertController
{
    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value="/expert/{userId}")
    public Expert getExpertInfo(@PathVariable("userId") int userId)
    {
        User user = userRepository.findByUserId(userId);
        Expert expert=expertRepository.getByUser(user);
        if(expert!=null)
        return expert;
        else {
            expert = new Expert();
            expert.setIntroducation("查无此人");
            return expert;
        }
    }

    @PutMapping(value="/expert/{userid}")
    public Expert changeExpertInfo(@RequestBody ExExpert expert,@PathVariable("userid") int userid)
    {
        User user = userRepository.findByUserId(userid);
        Expert expert1=expertRepository.getByUser(user);
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
            if(expert.getPaper()!=null)
                expert1.setPaper(expert.getPaper());
            if(expert.getPatent()!=null)
                expert1.setPatent(expert.getPatent());
            if(expert.getProject()!=null)
                expert1.setProject(expert.getProject());
            expertRepository.save(expert1);
        }
        else{
            expert1=new Expert();
            expert1.setIntroducation("查无此人");
        }
        return expert1;
    }


    @PostMapping(value = "/expert")
    public Boolean addExpert(@RequestBody ExExpert exExpert)
    {
        Expert expert1=new Expert();
        User user=userRepository.findByUserId(exExpert.getUserId());
        Expert expert2 = expertRepository.getByUser(user);
        if( user!=null && expert2==null)
        {
            expert1.setUser(user);
            expert1.setExpertId(exExpert.getExpertId());
            expert1.setField(exExpert.getField());
            expert1.setIntroducation(exExpert.getIntroducation());
            expert1.setInstitution(exExpert.getInstitution());
            expert1.setOtherAchievement(exExpert.getOtherAchievement());
            expert1.setPaper(exExpert.getPaper());
            expert1.setProject(exExpert.getProject());
            expert1.setPatent(exExpert.getPatent());
            expertRepository.save(expert1);
            return true;
        }
        else
        {
            return false;
        }

    }
}
