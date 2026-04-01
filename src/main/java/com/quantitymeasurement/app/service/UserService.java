package com.quantitymeasurement.app.service;

import com.quantitymeasurement.app.dto.LoginDto;
import com.quantitymeasurement.app.dto.RegisterDto;
import com.quantitymeasurement.app.entity.User;

public interface UserService {
    User register(RegisterDto user);
    User login(LoginDto user);
    User profile(Long id);
}
