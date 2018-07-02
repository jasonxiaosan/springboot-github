package com.jason.Controller;

import com.jason.Service.UserServiceImpl;
import com.jason.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping(value = "/registry",method = RequestMethod.POST)
    public User createUser(@RequestParam("username") String username,@RequestParam("password") String password){
        User us = new User();
        us.setUsername(username);
        us.setPassword(password);
        return userService.create(us);
    }
}
