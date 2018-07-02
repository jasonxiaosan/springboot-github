package com.jpa.Controller;

import com.jpa.Model.User;
import com.jpa.Service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Created by Administrator on 2018/6/21.
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @ApiOperation(value = "用户列表",notes = "用户列表")
    @RequestMapping(value = {""},method = RequestMethod.GET)
    public List<User> getUsers(){
        List<User> users = userService.findAll();
        return users;
    }
@ApiOperation(value = "创建用户",notes = "创建用户")
@RequestMapping(value = "",method = RequestMethod.POST)
public User postUser(@RequestBody User user){
        return userService.saveUser(user);
}
@ApiOperation(value = "更新信息",notes = "根据ID更新用户信息")
@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
public User putUser(@PathVariable Long id,@PathVariable User user){
    User user1= new User();
    user1.setUsername(user.getUsername());
    user1.setPassword(user.getPassword());
    user1.setId(id);
    return userService.updateUser(user1);
}
@ApiIgnore
@RequestMapping(value = "/hi",method = RequestMethod.GET)
public String jsonTest(){
    return "hi,jason";
}


    @GetMapping("/{username}")
    public User getUser(@PathVariable("username")String username){
        return userService.findUserByName(username);
    }
}
