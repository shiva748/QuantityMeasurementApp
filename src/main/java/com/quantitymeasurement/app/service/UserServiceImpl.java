package com.quantitymeasurement.app.service;

import com.quantitymeasurement.app.dto.LoginDto;
import com.quantitymeasurement.app.dto.RegisterDto;
import com.quantitymeasurement.app.entity.User;
import com.quantitymeasurement.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(RegisterDto registerDto) {
        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }
        User user = User.builder().email(registerDto.getEmail()).firstName(registerDto.getFirstName()).lastName(registerDto.getLastName()).password(registerDto.getPassword()).build();
        return userRepository.save(user);
    }

    @Override
    public User login(LoginDto loginDto) {
        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());
        if(user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return user.get();
    }

    @Override
    public User profile(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return user.get();
    }
}
