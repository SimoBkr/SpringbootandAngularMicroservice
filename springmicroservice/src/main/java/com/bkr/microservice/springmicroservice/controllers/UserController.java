package com.bkr.microservice.springmicroservice.controllers;

import com.bkr.microservice.springmicroservice.entities.UserEntity;
import com.bkr.microservice.springmicroservice.requests.UserRequest;
import com.bkr.microservice.springmicroservice.responses.UserResponse;
import com.bkr.microservice.springmicroservice.services.UserService;
import com.bkr.microservice.springmicroservice.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/{userid}",produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<UserResponse> getUser(@PathVariable String userid) {

       UserDto userDto = userService.getUserByUserId(userid);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(userDto,userResponse);

        return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest,userDto);

        UserDto createUser = userService.createUser(userDto);

        UserResponse userResponse = new UserResponse();

        BeanUtils.copyProperties(createUser,userResponse);

        return new ResponseEntity<UserResponse>(userResponse,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{userid}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String userid ,@RequestBody UserRequest userRequest) {

        UserDto userDto = userService.getUserByUserId(userid);
        BeanUtils.copyProperties(userRequest,userDto);

        UserDto updateUser = userService.updateUser(userid ,userDto);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(updateUser,userResponse);

        return new ResponseEntity<UserResponse>(userResponse,HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{userid}")
    public ResponseEntity<Object> deleteUser(@PathVariable String userid) {

        userService.deleteUser(userid);

        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }


}
