package com.medimind.medimindbackend.controller;

import com.medimind.medimindbackend.dto.ForgotPasswordRequest;
import com.medimind.medimindbackend.dto.LoginRequest;
import com.medimind.medimindbackend.dto.RegisterRequest;
import com.medimind.medimindbackend.dto.ResetPasswordRequest;
import com.medimind.medimindbackend.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(
            @RequestBody ForgotPasswordRequest request
    ) {

        return authService.forgotPassword(
                request.getEmail()
        );
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestBody ResetPasswordRequest request
    ) {

        return authService.resetPassword(
                request.getToken(),
                request.getNewPassword()
        );
    }
}