package com.bkr.microservice.springmicroservice.controllers;

import com.bkr.microservice.springmicroservice.requests.UserRequest;
import com.bkr.microservice.springmicroservice.responses.UserResponse;
import com.bkr.microservice.springmicroservice.services.UserService;
import com.bkr.microservice.springmicroservice.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String getUser() {
        return "get user";
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) {


        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest,userDto);

        UserDto createUser = userService.createUser(userDto);

        UserResponse userResponse = new UserResponse();

        BeanUtils.copyProperties(createUser,userResponse);

        return userResponse;
    }

    @PutMapping
    public String updateUser() {
        return "update user";
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete user";
    }


}
