package com.bkr.microservice.springmicroservice.services;

import com.bkr.microservice.springmicroservice.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService extends UserDetailsService{

    UserDto createUser(UserDto userDto);

    UserDto getUser(String email);

    UserDto getUserByUserId(String userid);

    UserDto updateUser(String userid ,UserDto userDto);

    String deleteUser(String userid);

    List<UserDto> getUsers(int page,int limit);
}


