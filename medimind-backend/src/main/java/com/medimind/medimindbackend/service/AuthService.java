package com.medimind.medimindbackend.service;


import org.springframework.http.ResponseEntity;
import java.util.UUID;
import com.medimind.medimindbackend.utils.JwtUtil;
import com.medimind.medimindbackend.dto.LoginRequest;
import com.medimind.medimindbackend.dto.RegisterRequest;
import com.medimind.medimindbackend.model.User;
import com.medimind.medimindbackend.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public AuthService(EmailService emailService, UserRepository userRepository, JwtUtil jwtUtil) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already exists";
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(request.getRole());

        userRepository.save(user);

        return "User Registered Successfully";
    }

    public Object login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            return "User not found";
        }

        boolean matched = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matched) {
            return "Invalid Password";
        }

        String token = jwtUtil.generateToken(user.getEmail());

        Map<String, Object> response = new HashMap<>();

        response.put("token", token);
        response.put("role", user.getRole());
        response.put("email", user.getEmail());
        response.put("name", user.getName());

        return response;
    }
    public ResponseEntity<?> forgotPassword(String email) {

        User user = userRepository.findByEmail(email)
                .orElse(null);

        if (user == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Email not found");
        }

        String token = UUID.randomUUID().toString();

        user.setResetToken(token);

        userRepository.save(user);

        String resetLink =
                "http://localhost:5173/reset-password/" + token;

        emailService.sendEmail(
                email,
                "Password Reset",
                "Click this link to reset password:\n" + resetLink
        );

        return ResponseEntity.ok(
                "Reset link sent to email"
        );
    }
    public ResponseEntity<?> resetPassword(
            String token,
            String newPassword
    ) {

        User user = userRepository
                .findByResetToken(token)
                .orElse(null);

        if (user == null) {

            return ResponseEntity
                    .badRequest()
                    .body("Invalid token");
        }

        user.setPassword(
                passwordEncoder.encode(newPassword)
        );

        user.setResetToken(null);

        userRepository.save(user);

        return ResponseEntity.ok(
                "Password reset successful"
        );
    }
}