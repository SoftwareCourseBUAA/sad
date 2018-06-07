package com.expert.demo.Repository;

import com.expert.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer>
{
    @Query("select COUNT(u) from User u where u.nickname=?1")
    public int getNumberOfNickname(String nickname);

    public User findByNickname(String nickname);

    public User findByUserId(int userId);

    public List<User> findUsersByNameLike(String name);
}
