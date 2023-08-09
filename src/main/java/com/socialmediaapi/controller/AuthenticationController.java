package com.socialmediaapi.controller;

import com.socialmediaapi.aspect.ToLog;
import com.socialmediaapi.dto.AuthenticationRequestDto;
import com.socialmediaapi.exception.NotFoundException;
import com.socialmediaapi.model.User;
import com.socialmediaapi.repository.UserRepository;
import com.socialmediaapi.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
@ToLog
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));

            User user = userRepository.findByUsername(username).orElseThrow(() ->
                    new NotFoundException(String.format("User with username %s not found", username)));

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            return Map.of("username", username,
                    "token", token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
