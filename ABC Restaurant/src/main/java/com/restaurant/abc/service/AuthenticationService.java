package com.restaurant.abc.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean authenticate(String user, String password) {
        boolean isValidUserName = user.equalsIgnoreCase("admin");
        boolean isValidPassword = password.equalsIgnoreCase("admin");
        return isValidUserName && isValidPassword;
    }
}
