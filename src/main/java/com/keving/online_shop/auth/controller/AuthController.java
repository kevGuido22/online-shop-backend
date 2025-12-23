package com.keving.online_shop.auth.controller;

import com.keving.online_shop.auth.dto.RegisterRequestDTO;
import com.keving.online_shop.auth.dto.RegisterResponseDTO;
import com.keving.online_shop.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody RegisterRequestDTO registerDto){
        RegisterResponseDTO userSaved = authService.register(registerDto);

        Map<String, Object> response = new HashMap<>();

        response.put("message", "User successfully registered");
        response.put("statusCode", 201);
        response.put("data", userSaved);

        return ResponseEntity.status(201).body(response);
    }
}
