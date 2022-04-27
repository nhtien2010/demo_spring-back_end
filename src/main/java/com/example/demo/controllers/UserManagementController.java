package com.example.demo.controllers;

import com.example.demo.dtos.requests.RegisterRequestDto;
import com.example.demo.dtos.requests.UpdateUserRequestDto;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserManagementController {
    final private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@Valid @RequestParam("userId") Long userId){

        return ResponseEntity.ok(userService.getUser(userId));

    }
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserRequestDto dto){

        return ResponseEntity.ok(userService.updateUser(dto));

    }
    @PreAuthorize(value = "hasRole('ADMIN')")
    @RequestMapping(value = "/lock", method = RequestMethod.PUT)
    public ResponseEntity<?> lockUser(@Valid @RequestParam("userId") Long userId){

        return ResponseEntity.ok(userService.lockUser(userId));

    }
    @PreAuthorize(value = "hasRole('ADMIN')")
    @RequestMapping(value = "/unlock", method = RequestMethod.PUT)
    public ResponseEntity<?> unlockUser(@Valid @RequestParam("userId") Long userId){

        return ResponseEntity.ok(userService.unlockUser(userId));

    }
    @PreAuthorize(value = "hasRole('ADMIN')")
    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public ResponseEntity<?> deleteUser(@Valid @RequestParam("userId") Long userId){

        return ResponseEntity.ok(userService.deleteUser(userId));

    }

}
