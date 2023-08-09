package com.socialmediaapi.service;

import java.util.Map;

public interface AuthenticationService {

    Map<String, String> generateToken(String username, String password);
}
