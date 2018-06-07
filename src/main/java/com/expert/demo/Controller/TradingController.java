package com.expert.demo.Controller;

import com.expert.demo.AssitClass.ExTrading;
import com.expert.demo.Entity.Achievement;
import com.expert.demo.Entity.Trading;
import com.expert.demo.Entity.User;
import com.expert.demo.Repository.AchievementRepository;
import com.expert.demo.Repository.TradingRepository;
import com.expert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


public class TradingController
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TradingRepository tradingRepository;

    @Autowired
    private AchievementRepository achievementRepository;


    private int getPointByUserId( int userId )
    {
        User user=userRepository.findByUserId(userId);
        if( user!=null )
        {
            return user.getPoint();
        }
        else
        {
            return 0;
        }
    }

    //充值函数
    @PutMapping(value = "/point")
    @Transactional
    public Boolean topUp(@RequestBody ExTrading exTrading)
    {
        int userId=exTrading.getUserId();
        int point=exTrading.getPoint();
        User user1=userRepository.findByUserId(userId);
        if( user1==null&&point<=0 )
        {
            return false;
        }
        else
        {
            int currentPoint=user1.getPoint();
            user1.setPoint(currentPoint+point);
            userRepository.save(user1);
            return true;
        }
    }

    //购买成果函数
    @PutMapping(value="/trading")
    @Transactional
    public String buyAchievement(@RequestBody ExTrading exTrading)
    {
        int userId=exTrading.getUserId();
        int point=exTrading.getPoint();
        int achievementId=exTrading.getAchievementId();
        User user=userRepository.findByUserId(userId);
        Achievement achievement=achievementRepository.getOne(achievementId);
        if( user!=null&&achievement!=null )
        {
            if(user.getPoint()>=point&&achievement.getPoint()==point )
            {
                int currentPoint=user.getPoint();
                user.setPoint(currentPoint-point);
                userRepository.save(user);
                Trading trading=new Trading();
                trading.setUser(user);
                trading.setAchievement(achievement);
                tradingRepository.save(trading);
                return "success";
            }
            else
            {
                return "point is no enough!";
            }
        }
        else
        {
            return "fail";
        }
    }

    //获取用户积分函数
    @GetMapping(value = "/userPoint")
    public int getUserPoint( @RequestParam("userId") int userId )
    {
        return getPointByUserId(userId);
    }

    //查询对于特定用户的特定资源的交易记录存在与否
    @GetMapping(value = "/trading")
    public Boolean getExistenceOfTrading( @RequestParam("userId") int userId, @RequestParam("achievementId") int achievementId )
    {
        User user=userRepository.findByUserId(userId);
        Achievement achievement=achievementRepository.getOne(achievementId);
        if(user!=null&& achievement!=null)
            return tradingRepository.getByUserAndAchievement(user,achievement)!=null;
        else
            return false;
    }

    //查询特定用户的全部交易记录
    @GetMapping(value = "/trading/user/{userId}")
    public List<Trading> getTradingsByUser(@PathVariable("userId") int userId )
    {
        List<Trading> tradingList=new ArrayList<>();
        User user=userRepository.getOne(userId);
        if( user!=null )
        {
            tradingList=tradingRepository.findTradingsByUser(user);
            return tradingList;
        }
        else
        {
            return tradingList;
        }
    }

    //查询特定资源的交易记录
    @GetMapping(value = "/trading/achievement/{achievementId}")
    public List<Trading> getTradingsByAchievement(@PathVariable("achievementId") int achievementId )
    {
        List<Trading> tradingList=new ArrayList<>();
        Achievement achievement=achievementRepository.getOne(achievementId);
        if( achievement!=null )
        {
            tradingList=tradingRepository.findTradingsByAchievement(achievement);
            return tradingList;
        }
        else
        {
            return tradingList;
        }
    }

    //查找特定achievementId资源信息的函数
    @GetMapping(value = "/achievement/{achievementId}")
    public Achievement getInformationByAchievementId(@PathVariable("achievementId") int achievementId )
    {
        Achievement achievement=achievementRepository.getOne(achievementId);
        if( achievement!=null )
        {
            return  achievement;
        }
        else
        {
            Achievement achievement1=new Achievement();
            achievement1.setAchievementId(achievementId);
            return achievement1;
        }
    }

    //用于下载的函数，成功返回下载链接，失败返回字符串fail
    @GetMapping(value = "/trading/download")
    public String getDownloadUrl(@RequestParam("userId") int userId, @RequestParam("achievementId") int achievementId)
    {
        User user=userRepository.getOne(userId);
        Achievement achievement=achievementRepository.getOne(achievementId);
        if( user!=null&&achievement!=null )
        {
            if( achievement.getPoint()==0 )
                return achievement.getDownloadUrl();
            if( tradingRepository.getByUserAndAchievement(user,achievement)!=null )
            {
                return achievement.getDownloadUrl();
            }
            else
            {
                return "fail";
            }
        }
        else
        {
            return "fail";
        }
    }
}
