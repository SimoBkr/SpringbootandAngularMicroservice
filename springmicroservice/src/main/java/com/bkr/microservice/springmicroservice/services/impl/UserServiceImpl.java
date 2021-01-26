package com.bkr.microservice.springmicroservice.services.impl;

import com.bkr.microservice.springmicroservice.Repository.UserRepository;
import com.bkr.microservice.springmicroservice.entities.UserEntity;
import com.bkr.microservice.springmicroservice.services.UserService;
import com.bkr.microservice.springmicroservice.shared.dto.AddressDto;
import com.bkr.microservice.springmicroservice.shared.dto.UserDto;
import com.bkr.microservice.springmicroservice.shared.dto.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        for(int i=0; i<user.getAddresses().size(); i++) {
            AddressDto address = user.getAddresses().get(i);
            address.setUser(user);
            address.setAddressId(utils.generateStringId(32));
            user.getAddresses().set(i,address);
        }

        user.getContacts().setContactId(utils.generateStringId(30));

        user.getContacts().setUser(user);

        ModelMapper modelMapper = new ModelMapper();

        UserEntity userEntity = modelMapper.map(user,UserEntity.class);

        userEntity.setEncryptedpassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userEntity.setUserId(utils.generateStringId(32));

        UserEntity newUserEntity = userRepository.save(userEntity);

        UserDto userDto =  modelMapper.map(newUserEntity, UserDto.class);

        return userDto;
    }

    @Override
    public List<UserDto> getUsers(int page, int limit,String search,int status) {

        if(page >0) page -=1;

        List<UserDto> usersResponse = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, limit);

        Page<UserEntity> userPage ;

        if(search.isEmpty()) {
             userPage = userRepository.findAllUsers(pageable);
        }else {
             userPage = userRepository.findAllByCriteria(pageable,search,status);
        }

        List<UserEntity>users = userPage.getContent();

        for(UserEntity userEntity : users) {
           // UserDto userDto = new UserDto();

            ModelMapper modelMapper = new ModelMapper();
            UserDto user =  modelMapper.map(userEntity,UserDto.class);

            usersResponse.add(user);
        }

        return usersResponse;
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
