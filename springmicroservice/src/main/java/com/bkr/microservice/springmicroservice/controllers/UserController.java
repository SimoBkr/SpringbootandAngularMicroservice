package com.bkr.microservice.springmicroservice.controllers;

import com.bkr.microservice.springmicroservice.entities.UserEntity;
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

    @GetMapping(path = "/{userid}")
    public UserResponse getUser(@PathVariable String userid) {

       UserDto userDto = userService.getUserByUserId(userid);

//       UserResponse userResponse = new UserResponse(userDto.getUserId(),userDto.getUserName(),userDto.getLastName(),userDto.getEmail());
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(userDto,userResponse);

        return userResponse;
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

    @PutMapping(path = "/{userid}")
    public UserResponse updateUser(@PathVariable String userid ,@RequestBody UserRequest userRequest) {

        UserDto userDto = userService.getUserByUserId(userid);
        BeanUtils.copyProperties(userRequest,userDto);

        UserDto updateUser = userService.updateUser(userid ,userDto);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(updateUser,userResponse);

        return userResponse;
    }

    @DeleteMapping(path = "/{userid}")
    public String deleteUser(@PathVariable String userid) {

        return userService.deleteUser(userid);
    }


}
