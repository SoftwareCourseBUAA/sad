package com.expert.demo.Repository;

import com.expert.demo.Entity.User;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer>
{
    @Query("select COUNT(u) from User u where u.nickname=?1")
    public int getNumberOfNickname(String nickname);

    public User findByNickname(String nickname);

    public User findByUserId(int userId);

    public Page<User> findUsersByNameContaining(String name, Pageable pageable);
}
