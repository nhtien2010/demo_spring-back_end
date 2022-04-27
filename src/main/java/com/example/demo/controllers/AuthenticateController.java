package com.example.demo.controllers;

import com.example.demo.dtos.requests.LoginRequestDto;
import com.example.demo.dtos.requests.RegisterAdminRequestDto;
import com.example.demo.dtos.requests.RegisterUserRequestDto;
import com.example.demo.services.AuthenticateService;
import com.example.demo.services.UserService;
import com.example.demo.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthenticateController {

    final private AuthenticateService authenticateService;
    //final private UserService userService;


    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ResponseEntity<?> register(@Valid @RequestBody RegisterUserRequestDto request){
        return ResponseEntity.ok(authenticateService.register(request));
    }
    @RequestMapping(value = "/registerAdmin", method = RequestMethod.POST)
    public ResponseEntity<?> register(@Valid @RequestBody RegisterAdminRequestDto request){
        return ResponseEntity.ok(authenticateService.register(request));
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.PUT)
    public ResponseEntity<?> refreshToken(@Valid @RequestBody LoginRequestDto request){
        return ResponseEntity.ok(authenticateService.authenticate(request));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto request){
        return ResponseEntity.ok(authenticateService.authenticate(request));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.PUT)
    public ResponseEntity<?> logout(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(authenticateService.logOut(userId));
    }


}
