package com.example.demo.Services;

import com.example.demo.mapper.UsersMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UsersMapper usersMapper;
    public boolean verifyLogin(String username, String password) {
        User user = usersMapper.isLoggedUser(username, password);
        return user!=null;
    }

    public List<User> getAllUsers() {
        return usersMapper.getAllUser();
    }

//    public boolean getUserByUsername(String username) {
//        return usersMapper.findByUsername(username);
//    }

    public void insertUser(User user) {
        usersMapper.insert(user);
    }
}
