package com.socialmediaapi.mapper;

import com.socialmediaapi.dto.AdminUserDto;
import com.socialmediaapi.dto.UserDto;
import com.socialmediaapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    AdminUserDto fromUserToAdminDto(User user);

    UserDto fromUserToUserDto(User user);
}
