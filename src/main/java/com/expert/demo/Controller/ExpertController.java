package com.expert.demo.Controller;

import com.expert.demo.AssitClass.ExExpert;
import com.expert.demo.Entity.Expert;
import com.expert.demo.Entity.User;
import com.expert.demo.Repository.ExpertRepository;
import com.expert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ExpertController
{
    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/expert")
    public Boolean addExpert(@RequestBody ExExpert exExpert)
    {
        Expert expert1=new Expert();
        User user=userRepository.findByUserId(exExpert.getUserId());
        if( user!=null )
        {
            expert1.setUser(user);
            expertRepository.save(expert1);
            return true;
        }
        else
        {
            return false;
        }

    }
}
