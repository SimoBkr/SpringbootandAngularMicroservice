package com.bkr.microservice.springmicroservice.services.impl;

import com.bkr.microservice.springmicroservice.Repository.UserRepository;
import com.bkr.microservice.springmicroservice.entities.UserEntity;
import com.bkr.microservice.springmicroservice.services.UserService;
import com.bkr.microservice.springmicroservice.shared.dto.UserDto;
import com.bkr.microservice.springmicroservice.shared.dto.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserDto createUser(UserDto user) {

        UserEntity checkUser = userRepository.findByEmail(user.getEmail());

        if(checkUser != null) throw  new RuntimeException("the user Alredy exist!");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user , userEntity);

        userEntity.setEncryptedpassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserId(utils.generateStringId(32));

        UserEntity newUserEntity = userRepository.save(userEntity);

        UserDto userDtoController = new UserDto();

        BeanUtils.copyProperties(newUserEntity,userDtoController);

        return userDtoController;
    }

    @Override
    public UserDto getUser(String email) {

        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null) throw new UsernameNotFoundException(email);

        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userEntity, userDto);

        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userid) {

        UserEntity userEntity = userRepository.findByUserId(userid);
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userEntity,userDto);

        return userDto;
    }

    @Override
    public UserDto updateUser(String userid , UserDto userDto) {

        UserEntity userEntity = userRepository.findByUserId(userid);

        if(userEntity == null) throw new UsernameNotFoundException(userid);

        userEntity.setUserName(userDto.getUserName());
        userEntity.setLastName(userDto.getLastName());

        userRepository.save(userEntity);

        UserDto userUpdated = new UserDto();
        BeanUtils.copyProperties(userEntity,userUpdated);

        return userUpdated;
    }

    @Override
    public String deleteUser(String userid) {

        UserEntity userEntity = userRepository.findByUserId(userid);
        if(userEntity == null) throw new UsernameNotFoundException(userid);

        userRepository.delete(userEntity);

        return "UserDeleted";
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity =  userRepository.findByEmail(email);

        if(userEntity == null) throw new UsernameNotFoundException(email);

        return new User(userEntity.getEmail(),userEntity.getEncryptedpassword(),new ArrayList<>());
    }
}
