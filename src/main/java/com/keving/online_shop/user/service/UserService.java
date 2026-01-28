package com.keving.online_shop.user.service;

import com.keving.online_shop.user.dto.UserCreateDTO;
import com.keving.online_shop.user.dto.UserResponseDTO;
import com.keving.online_shop.user.mapper.UserMapper;
import com.keving.online_shop.user.model.User;
import com.keving.online_shop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDTO getMe(User user){
        return userMapper.userToUserResponseDto(user);
    }
}
