package com.socialmediaapi.controller;

import com.socialmediaapi.aspect.ToLog;
import com.socialmediaapi.dto.AdminUserDto;
import com.socialmediaapi.exception.NotFoundException;
import com.socialmediaapi.mapper.UserMapper;
import com.socialmediaapi.model.User;
import com.socialmediaapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/admin")
@RequiredArgsConstructor
@ToLog
public class AdminController {

    private final UserRepository userRepository;

    @GetMapping(value = "/users/{id}")
    public AdminUserDto getUserById(@PathVariable(name = "id") Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Use with id %s not found", id)));

        return UserMapper.INSTANCE.fromUserToAdminDto(user);
    }
}
