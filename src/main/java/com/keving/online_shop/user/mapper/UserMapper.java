package com.keving.online_shop.user.mapper;

import com.keving.online_shop.user.dto.UserCreateDTO;
import com.keving.online_shop.user.dto.UserResponseDTO;
import com.keving.online_shop.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO userToUserResponseDto(User user);
    User userCreateDTOToUser(UserCreateDTO userCreateDTO);
}
