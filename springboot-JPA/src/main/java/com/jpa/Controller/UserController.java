package com.jpa.Controller;

import com.jpa.Model.User;
import com.jpa.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/6/21.
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username")String username){
        return userService.findUserByName(username);
    }
}
