package com.socialmediaapi.security;

import com.socialmediaapi.exception.NotFoundException;
import com.socialmediaapi.model.User;
import com.socialmediaapi.repository.UserRepository;
import com.socialmediaapi.security.jwt.JwtUserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new NotFoundException(String.format("User with username %s not found", username)));

        return JwtUserFactory.create(user);
    }
}
