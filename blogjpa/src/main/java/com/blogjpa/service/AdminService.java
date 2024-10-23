package com.blogjpa.service;

import com.blogjpa.model.Admin;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    public boolean authenticate(String username, String password) {
        Admin admin = Admin.getInstance();
        return admin.getUsername().equals(username) && admin.checkPassword(password);
    }
}