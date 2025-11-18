package com.blbirla.app.controller;

import com.blbirla.app.entity.LoginRequest;
import com.blbirla.app.entity.User;
import com.blbirla.app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin // IMPORTANT: frontend can call backend
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
       return ResponseEntity.ok(this.userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest){
        return  ResponseEntity.ok(this.userService.login(loginRequest));
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }
}
