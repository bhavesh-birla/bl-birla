package com.blbirla.app.controller;


import com.blbirla.app.entity.LoginRequest;
import com.blbirla.app.entity.User;
import com.blbirla.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping
    ResponseEntity<User> registerUser(@RequestBody User user)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.registerUser(user));
    }

    @PostMapping
    ResponseEntity<User> login(@RequestBody LoginRequest loginRequest)
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(this.userService.login(loginRequest));
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<User> getUser(@PathVariable Long id){
        return ResponseEntity.ok(this.userService.getUser(id));
    }
}
