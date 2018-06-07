package com.expert.demo.Controller;

import com.expert.demo.AssitClass.CustomizedUser;
import com.expert.demo.Entity.User;
import com.expert.demo.Repository.ExpertRepository;
import com.expert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserController
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpertRepository expertRepository;

    //用户注册函数
    @PostMapping(value = "/users")
    public Boolean userAdd(@RequestBody User user)
    {
        User user1=new User();

        //昵称重复
        if( userRepository.getNumberOfNickname(user.getNickname())>0 )
            return false;
        else
        {
            user1.setNickname(user.getNickname());
            user1.setPassword(user.getPassword());
            user1.setPoint(0);
            userRepository.save(user1);
            return true;
        }
    }

    //用户登录函数, 正确返回用户id，错误返回0
    @GetMapping(value = "/users")
    public int userExistence(@RequestParam("nickname") String nickname, @RequestParam("password") String password)
    {
        User user1=userRepository.findByNickname(nickname);
        if( user1!=null&&password.equals(user1.getPassword()))
        {
            return user1.getUserId();
        }
        else
        {
            return 0;
        }
    }

    //用户信息展示函数
    @GetMapping(value = "/userInformation")
    public CustomizedUser getUserInformation(@RequestParam("userId") int userId)
    {
        User user=userRepository.findByUserId(userId);
        CustomizedUser exUser=new CustomizedUser();
        if( user!=null)
        {
            user.setPassword("");
            exUser.setUser(user);
            if( expertRepository.getNumberOfExpertByUser(user)>0 )
            {
                exUser.setIdentity(2);
            }
            else
            {
                exUser.setIdentity(1);
            }
        }
        else
        {
            User user1=new User();
            user1.setUserId(userId);
            exUser.setUser(user1);
            exUser.setIdentity(1);
        }
        return exUser;
    }

    //修改用户信息的函数,能修改email，name，nickname和password
    @PutMapping(value = "/userInformation")
    public User modifyUserInformation(@RequestBody User user)
    {
        User user1=userRepository.findByUserId(user.getUserId());
        if( user1!=null )
        {
            if( user.getEmail()!=null)
                user1.setEmail(user.getEmail());
            if( user.getName()!=null )
                user1.setName(user.getName());
            if( user.getPassword()!=null)
                user1.setPassword(user.getPassword());
            if( user.getNickname()!=null )
            {
                if( userRepository.getNumberOfNickname(user.getNickname())==0 )
                {
                    user1.setNickname(user.getNickname());
                }
            }
            userRepository.save(user1);
        }
        else
        {
            user1=new User();
            user1.setUserId(user.getUserId());
        }
        user1.setPassword("");
        return user1;
    }
}
