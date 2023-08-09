package com.socialmediaapi.controller;

import com.socialmediaapi.aspect.ToLog;
import com.socialmediaapi.dto.AdminUserDto;
import com.socialmediaapi.mapper.UserMapper;
import com.socialmediaapi.model.User;
import com.socialmediaapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin")
@RequiredArgsConstructor
@ToLog
public class AdminController {

    private final UserService userService;

    @GetMapping(value = "/users/{id}")
    public AdminUserDto getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        return UserMapper.INSTANCE.fromUserToAdminDto(user);
    }
}
