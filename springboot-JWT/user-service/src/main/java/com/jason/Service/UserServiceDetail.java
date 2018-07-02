package com.jason.Service;

import com.jason.Conmmon.BPwdEncoderUtil;
import com.jason.Conmmon.JWT;
import com.jason.Conmmon.UserLoginDTO;
import com.jason.Dao.AuthServiceClient;
import com.jason.Dao.UserDao;
import com.jason.Error.UserLoginException;
import com.jason.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDetail{
    @Autowired
    private UserDao userRepository;

    public User insertUser(String username,String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(BPwdEncoderUtil.BCryptPassword(password));
        return userRepository.save(user);
    }
   @Autowired
    AuthServiceClient client;
    public UserLoginDTO login(String username, String password){
        User user = userRepository.findByUsername(username);
        if(user==null)
        {
            throw new UserLoginException("error username");
        }
        if(!BPwdEncoderUtil.matches(password,user.getPassword())){
            throw new UserLoginException("error password");
        }
        JWT jwt = client.getToken("null","password",username,password);
        if(jwt==null){
            throw new UserLoginException("error jwt");
        }
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setJwt(jwt);
        userLoginDTO.setUser(user);
        return userLoginDTO;
    }
}
