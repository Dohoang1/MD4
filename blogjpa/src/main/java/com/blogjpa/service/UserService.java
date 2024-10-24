package com.blogjpa.service;

import com.blogjpa.model.User;
import com.blogjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            // In ra log để kiểm tra
            System.out.println("Found user: " + user.getUsername());
            System.out.println("Stored password: " + user.getPassword());
            System.out.println("Input password: " + password);
            return user.getPassword().equals(password);
        }
        return false;
    }
}