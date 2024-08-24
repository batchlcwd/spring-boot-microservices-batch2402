package com.elearn.app.controllers;

import com.elearn.app.dtos.UserDto;
import com.elearn.app.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto createUser(
            @RequestBody UserDto userDto
    ) {
        System.out.println("creating user");
        return userService.create(userDto);
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable String userId){

        return userService.geById(userId);
    }

}
