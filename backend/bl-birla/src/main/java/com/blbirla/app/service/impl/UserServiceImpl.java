package com.blbirla.app.service.impl;

import com.blbirla.app.dto.LoginRequest;
import com.blbirla.app.entity.User;
import com.blbirla.app.repository.UserRepository;
import com.blbirla.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User registerUser(User user) {

        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered!");
        }
        return userRepo.save(user);
    }

    @Override
    public User login(LoginRequest req) {

        User user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
System.out.println("user checked!");
System.out.println("password checking.....!");
        // Check bcrypt match
        if (!user.getPasswordHash().equals(req.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        System.out.println("password checking....Done111.!");
        return user;   // Login success
    }

    @Override
    public User getUser(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }
}
