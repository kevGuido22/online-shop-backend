package com.keving.online_shop.auth.controller;

import com.keving.online_shop.auth.dto.LoginRequestDTO;
import com.keving.online_shop.auth.dto.LoginResponseDTO;
import com.keving.online_shop.auth.dto.RegisterRequestDTO;
import com.keving.online_shop.auth.dto.RegisterResponseDTO;
import com.keving.online_shop.auth.model.RefreshToken;
import com.keving.online_shop.auth.repository.RefreshTokenRepository;
import com.keving.online_shop.auth.service.AuthService;
import com.keving.online_shop.auth.service.RefreshTokenService;
import com.keving.online_shop.exception.BusinessException;
import com.keving.online_shop.security.service.JwtService;
import com.keving.online_shop.user.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody RegisterRequestDTO registerDto){
        RegisterResponseDTO userSaved = authService.register(registerDto);

        Map<String, Object> response = new HashMap<>();

        response.put("message", "User successfully registered");
        response.put("statusCode", 201);
        response.put("data", userSaved);

        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        User authUser = authService.authenticate(loginRequestDTO);

        String jwtToken = jwtService.generateToken(authUser);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(authUser);

        LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder().token(jwtToken).expiresIn(jwtService.getExpirationTime()).refreshToken(refreshToken).build();

        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refreshToken(@RequestBody Map<String, String> payload){
        String requestToken = payload.get("refreshToken");

        RefreshToken refreshToken = refreshTokenRepository.findByToken(requestToken)
                .orElseThrow(() -> new BusinessException("Invalid refresh token", HttpStatus.UNAUTHORIZED.value()));

        refreshTokenService.verifyExpiration(refreshToken);

        User user = refreshToken.getUser();

        String newAccessToken = jwtService.generateToken(user);

        return ResponseEntity.ok(LoginResponseDTO.builder().token(newAccessToken).expiresIn(jwtService.getExpirationTime()).refreshToken(refreshToken).build());
    }
}
