package com.expert.demo.Repository;

import com.expert.demo.Entity.Achievement;
import com.expert.demo.Entity.Trading;
import com.expert.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TradingRepository extends JpaRepository<Trading,Integer>
{
    public Trading getByUserAndAchievement(User user,Achievement achievement);

    public List<Trading> findTradingsByUser(User user);

    public List<Trading> findTradingsByAchievement(Achievement achievement);
}

