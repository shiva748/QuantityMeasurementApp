package com.quantitymeasurement.app.controller;

import com.quantitymeasurement.app.dto.LoginDto;
import com.quantitymeasurement.app.dto.RegisterDto;
import com.quantitymeasurement.app.entity.User;
import com.quantitymeasurement.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto) {
        User user = userService.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        User user = userService.login(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
