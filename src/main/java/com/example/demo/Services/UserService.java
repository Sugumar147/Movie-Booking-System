package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public boolean verifyLogin(String username, String password) {
        if (username.equals("sugu") && password.equals("pass")) {
            return true; // Login successful
        }
        return false; // Login failed
    }
}
