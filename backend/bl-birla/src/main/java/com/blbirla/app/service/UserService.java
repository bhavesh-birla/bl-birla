package com.blbirla.app.service;

import com.blbirla.app.entity.LoginRequest;
import com.blbirla.app.entity.User;
import com.blbirla.app.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User registerUser(User user) {

        // 1. Check duplicate email
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists.");
        }

        // 2. Convert plain password -> passwordHash
        if (user.getPasswordHash() == null || user.getPasswordHash().isEmpty()) {
            throw new RuntimeException("Password cannot be empty");
        }

        // hash the incoming password (from frontend)
        String hashed = encoder.encode(user.getPasswordHash());
        user.setPasswordHash(hashed);

        // 3. Save user
        return userRepo.save(user);
    }



    public User login(LoginRequest loginRequest) {
        User user = userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new RuntimeException("can not find by email"));
        return user;
    }

    public User getUser(Long id) {
        return userRepo.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
    }
}