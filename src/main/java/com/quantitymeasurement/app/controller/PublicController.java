package com.quantitymeasurement.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {
    @GetMapping("/")
    public ResponseEntity<?> welcomeMessage() {
        return ResponseEntity.status(HttpStatus.OK).body("Welcome to Quantity Measurement");
    }
}
