package com.keving.online_shop.auth.mapper;

import com.keving.online_shop.auth.dto.RegisterResponseDTO;
import com.keving.online_shop.auth.dto.RegisterRequestDTO;
import com.keving.online_shop.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    RegisterResponseDTO userToAuthResponse(User user);
    User registerDtoToUser(RegisterRequestDTO registerRequestDTO);
}
