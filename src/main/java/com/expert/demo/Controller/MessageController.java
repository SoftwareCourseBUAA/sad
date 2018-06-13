package com.expert.demo.Controller;

import com.expert.demo.AssitClass.ExExpert;
import com.expert.demo.AssitClass.ExMessage;
import com.expert.demo.Entity.Administor;
import com.expert.demo.Entity.Expert;
import com.expert.demo.Entity.Message;
import com.expert.demo.Entity.User;
import com.expert.demo.Repository.AdministorRepository;
import com.expert.demo.Repository.ExpertRepository;
import com.expert.demo.Repository.MessageRepository;
import com.expert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin
public class MessageController
{
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdministorRepository administorRepository;

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private ExpertController expertController;

    /**
     *生成一封站内信
     * @param sender
     * @param receiver
     * @param type   1代表用户与专家，用户与用户的私聊。2代表申请成为专家的申请信，3代表申请管理门户的申请信。4代表管理员的回执信
     * @param content
     * @param theme
     * @return
     */
    public Boolean addMessage( User sender, User receiver, int type, String content, String theme )
    {
        try
        {
            Message message = new Message();
            message.setSender(sender);
            message.setReceiver(receiver);
            message.setContent(content);
            message.setType(type);
            message.setTheme(theme);
            message.setSendDate(new Date());
            messageRepository.save(message);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取某个人的全部收信
     * @param userId
     * @return
     */
    @GetMapping(value = "/message/receiver")
    public List<Message> getMessagesByReceiver(@RequestParam("userId") int userId )
    {
        User user=userRepository.findByUserId(userId);
        List<Message> messageList=new ArrayList<>();
        if( user!=null )
        {
            messageList=messageRepository.getMessagesByReceiverOrderBySendDateDesc(user);
            for( int i=0;i<messageList.size();i++ )
            {
                messageList.get(i).getSender().setPassword("");
                messageList.get(i).getReceiver().setPassword("");
            }
        }
        return messageList;
    }

    /**
     * 获取某个人的全部写信
     * @param userId
     * @return
     */
    @GetMapping(value = "/message/sender")
    public List<Message> getMessagesBySender(@RequestParam("userId") int userId )
    {
        System.out.println(userId);
        User user=userRepository.findByUserId(userId);
        System.out.println(user.getNickname());
        List<Message> messageList=new ArrayList<>();
        if( user!=null )
        {
            messageList=messageRepository.getMessagesBySenderOrderBySendDateDesc(user);
            System.out.println(messageList.size());
            for( int i=0;i<messageList.size();i++ )
            {
                messageList.get(i).getSender().setPassword("");
                messageList.get(i).getReceiver().setPassword("");
            }
        }
        return messageList;
    }

    /**
     * 添加申请成为专家的申请信函数
     * @param exMessage
     * @return
     */
    @PostMapping(value = "/applyMessage/expert")
    public Boolean addExpertApplyMessage( @RequestBody ExMessage exMessage )
    {
        try
        {
            int userId = exMessage.getUserId();
            User sender = userRepository.findByUserId(userId);
            if (sender != null)
            {
                List<Administor> administorList = administorRepository.findAll();
                Random random = new Random();
                int r1 = random.nextInt(administorList.size());
                User receiver = administorList.get(r1).getUser();
                addMessage(sender, receiver, 2, "用户" + sender.getName() + "申请成为专家",sender.getName()+"的申请信");
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 添加专家申请管理门户的申请信函数
     * @param exMessage
     * @return
     */
    @PostMapping(value = "/applyMessage/mangement")
    public Boolean addMangementApplyMessage(@RequestBody ExMessage exMessage)
    {
        try
        {
            int userId= exMessage.getUserId();
            User sender = userRepository.findByUserId(userId);
            if (sender != null)
            {
                Expert expert=expertRepository.findExpertByUser(sender);
                if( expert!=null )
                {
                    if( expert.getIsAuthenticated()==true )
                        return false;
                    else
                    {
                        List<Administor> administorList = administorRepository.findAll();
                        Random random = new Random();
                        int r1 = random.nextInt(administorList.size());
                        User receiver = administorList.get(r1).getUser();
                        addMessage(sender, receiver, 3, "专家" + sender.getName() + "申请管理自己的门户", sender.getName()+"的申请信");
                        return true;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加交流信的函数
     * @param exMessage
     * @return
     */
    @PostMapping(value = "/communicationMessage")
    public Boolean addCommunicationMessage( @RequestBody ExMessage exMessage )
    {
        try
        {
            String senderNickname = exMessage.getSender();
            String receiverNickname = exMessage.getReceiver();
            String content=exMessage.getContent();
            String theme=exMessage.getTheme();
            User sender = userRepository.findByNickname(senderNickname);
            User receiver = userRepository.findByNickname(receiverNickname);
            return addMessage(sender,receiver,1,content,theme);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 管理员对审核的回执信
     * @param exMessage
     * @return
    @PostMapping(value = "/replayMessage")
    @Transactional
    public Boolean addReplayMessage(@RequestBody ExMessage exMessage)
    {
        try
        {
            int messageId=exMessage.getMessageId();
            int agreement=exMessage.getAgreement();
            Message message=messageRepository.findByUserId(messageId);
            User sender=message.getSender();
            User receiver=message.getReceiver();
            int type=message.getType();
            if( type==2||type==3 )
            {
                if( agreement==1 )
                {
                    addMessage(receiver,sender,4,"你的申请已经通过");
                    if( type==2 )
                    {
                        ExExpert exExpert=new ExExpert();
                        exExpert.setUserId(sender.getUserId());
                        return expertController.addExpert(exExpert);
                    }
                    else
                    {
                        Expert expert=expertRepository.findExpertByUser(sender);
                        expert.setIsAuthenticated(true);
                        return true;
                    }
                }
                else if( agreement==2 )
                {
                    addMessage(receiver,sender,4,"你的申请未通过");
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    */

    /**
     * 根据userId拿到nickname
     * @param userId
     * @return
     */
    @GetMapping(value = "/nickname")
    public String getNicknameByUserId(@RequestParam ("userId") int userId )
    {
        try
        {
            User user=userRepository.findByUserId(userId);
            return user.getNickname();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "fail";
        }
    }
}
