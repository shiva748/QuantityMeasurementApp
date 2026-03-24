package com.quantitymeasurement.app.controller;

import com.quantitymeasurement.app.dto.LoginDto;
import com.quantitymeasurement.app.dto.RegisterDto;
import com.quantitymeasurement.app.entity.User;
import com.quantitymeasurement.app.security.jwt.JwtService;
import com.quantitymeasurement.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Value("${spring.application.token.expiry}")
    private int tokenExpiry;

    private final UserService userService;
    private final JwtService jwtService;
    @Autowired
    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto) {
        User user = userService.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        User user = userService.login(loginDto);
        String Token = jwtService.generateToken(user);
        return ResponseEntity.ok() .header( "Set-Cookie", String.format( "jwt=%s; Path=/; Max-Age=%d; HttpOnly; SameSite=None", Token, tokenExpiry ) ) .body("login was successful.");
    }
}
