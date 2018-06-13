package com.expert.demo.Repository;

import com.expert.demo.Entity.Message;
import com.expert.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer>
{
    public List<Message> getMessagesByReceiverOrderBySendDateDesc(User user);

    public List<Message> getMessagesBySenderOrderBySendDateDesc(User user);

}
