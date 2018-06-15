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
    public Expert getExpertInfoByUserId(@PathVariable("userid") int userid)
    {
        User user = userRepository.findByUserId(userid);
        Expert expert =new Expert();
        if(user!=null)
        {
            expert = expertRepository.getByUser(user);
            if(expert!=null)
            {
                return expert;
            }
            else{
                expert.setIntroducation("这个用户不对应任何专家");
            }
        }
        else{
            expert.setIntroducation("没有这个用户的信息");
        }
        return expert;
    }

    @PutMapping(value="/expert/{userid}")
    public Expert changeExpertInfo(@RequestBody Expert expert,@PathVariable("userid") int userid)
    {
        User user = userRepository.findByUserId(userid);
        if(user==null)
        {
            Expert e = new Expert();
            e.setIntroducation("没有这个用户");
            return e;
        }
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
            //if(expert.getPaper()!=null)
                //expert1.setPaper(expert.getPaper());
            if(expert.getPatent()!=null)
                expert1.setPatent(expert.getPatent());
            if(expert.getProject()!=null)
                expert1.setProject(expert.getProject());
            if(expert.getName()!=null)
            {
                expert1.setName(expert.getName());
                user.setName(expert.getName());
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
            if(expert.getField()!=null)
            expert1.setField(expert.getField());
            if(expert.getIntroducation()!=null)
            expert1.setIntroducation(expert.getIntroducation());
            if(expert.getInstitution()!=null)
            expert1.setInstitution(expert.getInstitution());
            if(expert.getOtherAchievement()!=null)
            expert1.setOtherAchievement(expert.getOtherAchievement());
            //if(expert.getPaper()!=null)
            //expert1.setPaper(expert.getPaper());
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
