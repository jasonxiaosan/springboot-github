package com.jpa.Dao;

import com.jpa.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/6/21.
 */
public interface  UserDao extends JpaRepository<User,Long>{
    User findByUsername(String username);
}
