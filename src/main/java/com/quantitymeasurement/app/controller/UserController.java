package com.quantitymeasurement.app.controller;

import com.quantitymeasurement.app.dto.ApiResponse;
import com.quantitymeasurement.app.dto.LoginDto;
import com.quantitymeasurement.app.dto.RegisterDto;
import com.quantitymeasurement.app.entity.User;
import com.quantitymeasurement.app.security.jwt.JwtService;
import com.quantitymeasurement.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
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

    @PostMapping("/auth/register")
    public ResponseEntity<ApiResponse<?>> registerUser(@RequestBody RegisterDto registerDto) {
        userService.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "User registered successfully!"));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponse<User>> loginUser(@RequestBody LoginDto loginDto) {
        User user = userService.login(loginDto);
        String Token = jwtService.generateToken(user);
        return ResponseEntity.ok()
                .header(
                        "Set-Cookie",
                        String.format(
                                "jwt=%s; Path=/; Max-Age=%d; HttpOnly; SameSite=Lax",
                                Token,
                                tokenExpiry
                        )
                )
                .body(new ApiResponse<>(true, "Login successfully!", user));
    }

    @GetMapping("/auth/session")
    public ResponseEntity<ApiResponse<User>> getSession(Authentication authentication) {
        if(authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(false, "No session found!"));
        }
        Long id = (Long) authentication.getPrincipal();
        User user = userService.profile(id);
        return ResponseEntity.ok().body(new  ApiResponse<>(true, "Session Found!", user));
    }

    @GetMapping("/auth/session/logout")
    public ResponseEntity<ApiResponse<?>> logoutSession() {
        return ResponseEntity.ok()
                .header("Set-Cookie", "jwt=; Max-Age=0; Path=/; HttpOnly" )
                .body(new ApiResponse<>(true, "Logged out successfully."));
    }
}
