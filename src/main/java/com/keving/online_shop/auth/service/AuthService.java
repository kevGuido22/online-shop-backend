package com.keving.online_shop.auth.service;

import com.keving.online_shop.auth.dto.LoginRequestDTO;
import com.keving.online_shop.auth.dto.RegisterResponseDTO;
import com.keving.online_shop.auth.dto.RegisterRequestDTO;
import com.keving.online_shop.auth.mapper.AuthMapper;
import com.keving.online_shop.exception.BusinessException;
import com.keving.online_shop.user.model.User;
import com.keving.online_shop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public RegisterResponseDTO register(RegisterRequestDTO registerRequestDto) {
        boolean existEmail = userRepository.existsByEmail(registerRequestDto.getEmail());
        if (existEmail) {
            throw new BusinessException("Email already taken", 409);
        }

        boolean existUsername = userRepository.existsByUsername(registerRequestDto.getUsername());
        if (existUsername) {
            throw new BusinessException("username already taken", 409);
        }

        User user = authMapper.registerDtoToUser(registerRequestDto);

        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

        User saved = userRepository.save(user);

        return authMapper.userToAuthResponse(saved);
    }

    public User authenticate(LoginRequestDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
