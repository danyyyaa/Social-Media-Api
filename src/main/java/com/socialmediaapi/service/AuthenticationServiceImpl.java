package com.socialmediaapi.service;

import com.socialmediaapi.exception.NotFoundException;
import com.socialmediaapi.model.User;
import com.socialmediaapi.repository.UserRepository;
import com.socialmediaapi.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Override
    public Map<String, String> generateToken(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
                    password));

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
