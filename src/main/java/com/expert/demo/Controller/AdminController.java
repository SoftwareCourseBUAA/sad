package com.expert.demo.Controller;


import com.expert.demo.Entity.Expert;
import com.expert.demo.Entity.User;
import com.expert.demo.Repository.ExpertRepository;
import com.expert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AdminController {

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/admin/expert")
    public List<Expert> getAllExperts()
    {
        return expertRepository.findAll();
    }

    @GetMapping(value="/admin/expert/name/{name}")
    public Expert getExpertInfoByName(@PathVariable("name") String name)
    {
        Expert expert=expertRepository.getByName(name);
        if(expert==null) {
            expert=new Expert();
            expert.setIntroducation("查无此人");
        }
        return expert;
    }

//    @GetMapping(value="/admin/expert/id/{userid}")
//    public Expert getExpertInfoByUserId(@PathVariable("userid") int userid)
//    {
//        User user = userRepository.findByUserId(userid);
//        Expert expert =new Expert();
//        if(user!=null)
//        {
//            expert = expertRepository.getByUser(user);
//            if(expert!=null)
//            {
//                return expert;
//            }
//            else{
//                expert.setIntroducation("这个用户不对应任何专家");
//            }
//        }
//        else{
//            expert.setIntroducation("没有这个用户的信息");
//        }
//        return expert;
//    }

//    @PutMapping(value="/admin/expert/{expertId}")
//    public Expert changeExpertInfo(@RequestBody Expert expert,@PathVariable("expertId") int expertid)
//    {
//        Expert expert1 = expertRepository.getByExpertId(expertid);
//        User user = userRepository.findByUserId(expert1.getUser().getUserId());
//        if(expert1==null)
//        {
//            Expert e = new Expert();
//            e.setIntroducation("没有这个用户");
//            return e;
//        }
//        else if(expert1!=null)
//        {
//            if( expert.getField()!=null)
//                expert1.setField(expert.getField());
//            if( expert.getInstitution()!=null )
//                expert1.setInstitution(expert.getInstitution());
//            if( expert.getIntroducation()!=null)
//                expert1.setIntroducation(expert.getIntroducation());
//            if( expert.getOtherAchievement()!=null )
//                expert1.setOtherAchievement(expert.getOtherAchievement());
//            //if(expert.getPaper()!=null)
//                //expert1.setPaper(expert.getPaper());
//            if(expert.getPatent()!=null)
//                expert1.setPatent(expert.getPatent());
//            if(expert.getProject()!=null)
//                expert1.setProject(expert.getProject());
//            if(expert.getName()!=null)
//            {
//                expert1.setName(expert.getName());
//                user.setName(expert.getName());
//            }
//            expertRepository.save(expert1);
//            userRepository.save(user);
//        }
//        else{
//            expert1=new Expert();
//            expert1.setIntroducation("查无此人");
//        }
//        return expert1;
//    }


//    @PostMapping(value = "/admin/expert")
//    public Expert addExpert(@RequestBody Expert expert)
//    {
//        Expert expert1=new Expert();
//        if(expert!=null && expert.getName()!=null&&expertRepository.getByName(expert.getName())==null)
//        {
//            expert1.setName(expert.getName());
//            if(expert.getField()!=null)
//                expert1.setField(expert.getField());
//            if(expert.getIntroducation()!=null)
//                expert1.setIntroducation(expert.getIntroducation());
//            if(expert.getInstitution()!=null)
//                expert1.setInstitution(expert.getInstitution());
//            if(expert.getOtherAchievement()!=null)
//                expert1.setOtherAchievement(expert.getOtherAchievement());
//            //if(expert.getPaper()!=null)
//                //expert1.setPaper(expert.getPaper());
//            if(expert.getProject()!=null)
//                expert1.setProject(expert.getProject());
//            if(expert.getPatent()!=null)
//                expert1.setPatent(expert.getPatent());
//            expertRepository.save(expert1);
//            return expert1;
//        }
//        else if(expert!=null && expert.getName()!=null && expertRepository.getByName(expert.getName())!=null)
//        {
//            expert1.setIntroducation("无法添加重名专家");
//            return expert1;
//        }
//        else{
//            expert1.setIntroducation("关键信息缺失，无法添加专家");
//            return expert1;
//        }
//    }

    @DeleteMapping(value="/admin/expert/id/{expertid}")
    public boolean deleteExpertById(@PathVariable("expertId") int expertid)
    {
        Expert e = expertRepository.getByExpertId(expertid);
        if(e==null)
            return false;
        expertRepository.delete(e);
        return true;
    }

    @DeleteMapping(value="/admin/expert/name/{name}")
    public boolean deleteExpertByName(@PathVariable("name") String name)
    {
        Expert e = expertRepository.getByName(name);
        if(e==null)
            return false;
        expertRepository.delete(e);
        return true;
    }



}
