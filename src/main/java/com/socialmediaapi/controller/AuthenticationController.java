package com.socialmediaapi.controller;

import com.socialmediaapi.aspect.ToLog;
import com.socialmediaapi.dto.AuthenticationRequestDto;
import com.socialmediaapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
//@RequestMapping(value = "/api/v1/auth")
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
@ToLog
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    //@PostMapping("/login")
    @PostMapping("/generate-token")
    public Map<String, String> generateToken(@RequestBody AuthenticationRequestDto requestDto) {
        return authenticationService.generateToken(requestDto.getUsername(), requestDto.getPassword());
    }
}
