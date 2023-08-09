package com.socialmediaapi.service;

import com.socialmediaapi.model.User;

import java.util.Collection;

public interface UserService {

    User register(User user);

    User findByUsername(String username);

    User findById(Long id);

    Collection<User> findAll();

    void delete(Long id);
}
