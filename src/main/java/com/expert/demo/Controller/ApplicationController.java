package com.expert.demo.Controller;

import com.expert.demo.Entity.Expert;
import com.expert.demo.Entity.User;
import com.expert.demo.Entity.Application;
import com.expert.demo.Repository.ExpertRepository;
import com.expert.demo.Repository.ApplicationRepository;
import com.expert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ApplicationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping(value="/admin/apply")
    public List<Application> getAllApplication()
    {
        return applicationRepository.findAll();
    }

    @GetMapping(value="/admin/count")
    public int getApplicationCount(){return applicationRepository.getAppNumber();}

    @GetMapping(value="/admin/apply/add/{userId}/{expertId}")
    public Application addApplication(@PathVariable("userId") int userid, @PathVariable("expertId") int expertid)
    {
        Application tobeexpert = new Application();
        User user = userRepository.findByUserId(userid);
//        if(user==null)
//        {
//            user = new User();
//            user.setName("can't find the user");
//        }
        Expert expert= expertRepository.getByExpertId(expertid);
//        if(expert==null)
//        {
//            expert = new Expert();
//            expert.setName("can't find the expert");
//        }
        if(user!=null && expert!=null) {
            tobeexpert.setUser(user);
            tobeexpert.setExpert(expert);
            applicationRepository.save(tobeexpert);
        }
        return tobeexpert;
    }

    @DeleteMapping(value="/admin/apply/{applyId}")
    public boolean rejectApplication(@PathVariable("applyId") int applyid)
    {
        Application t = applicationRepository.findByApplyId(applyid);
        if(t==null)
            return false;
        applicationRepository.delete(t);
        return true;
    }

    @GetMapping(value="/admin/apply/accept/{applyId}")
    public boolean acceptApplication(@PathVariable("applyId") int applyid)
    {
        Application t = applicationRepository.findByApplyId(applyid);
        if(t==null)
            return false;
        User user = t.getUser();
        Expert expert = t.getExpert();
        expert.setUser(user);
        if(user==null || expert==null)
            return false;
        expertRepository.save(expert);
        applicationRepository.delete(t);
        return true;
    }


}
