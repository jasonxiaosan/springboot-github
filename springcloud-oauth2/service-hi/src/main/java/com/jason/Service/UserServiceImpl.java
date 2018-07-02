package com.jason.Service;

import com.jason.Dao.UserDao;
import com.jason.IService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static  final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private UserDao userDao;
    @Override
    public com.jason.model.User create(com.jason.model.User user){
        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);
        com.jason.model.User u =userDao.save(user);
        return u;

    }
}
