package com.expert.demo.Repository;

import com.expert.demo.Entity.Message;
import com.expert.demo.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer>
{
    public Page<Message> getMessagesByReceiverOrderBySendDateDesc(User user, Pageable pageable);

    public Page<Message> getMessagesBySenderOrderBySendDateDesc(User user,Pageable pageable);

}
