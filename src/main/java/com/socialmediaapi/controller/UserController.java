package com.socialmediaapi.controller;

import com.socialmediaapi.dto.UserDto;
import com.socialmediaapi.mapper.UserMapper;
import com.socialmediaapi.model.User;
import com.socialmediaapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/{id}")
    public UserDto getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        return UserMapper.INSTANCE.fromUserToUserDto(user);
    }
}
