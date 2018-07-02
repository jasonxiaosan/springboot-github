package com.jason.Dao;


import com.jason.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long>{
User findByUsername(String username);
}
