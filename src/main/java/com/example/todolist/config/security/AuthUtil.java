package com.example.todolist.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {

    public static String getAuthenticatedEmail(){
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())){
            throw new IllegalArgumentException("No authenticated user");
        }

        return auth.getName();
    }
}
