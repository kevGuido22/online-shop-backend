package com.keving.online_shop.user.controller;

import com.keving.online_shop.user.dto.UserResponseDTO;
import com.keving.online_shop.user.model.User;
import com.keving.online_shop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> me(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(userService.getMe(user));
    }
}
