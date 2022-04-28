package com.example.demo.utils;

import com.example.demo.domains.UserModel;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtil {
    public UserModel getUserClaim(){
        return (UserModel) getAuthentication().getPrincipal();
    }
    private Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
