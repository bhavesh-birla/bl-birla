package com.blbirla.app.service;

import com.blbirla.app.dto.LoginRequest;
import com.blbirla.app.entity.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);
    User login(LoginRequest request);
    User getUser(Long id);

    List<User> getAllUser();
}

