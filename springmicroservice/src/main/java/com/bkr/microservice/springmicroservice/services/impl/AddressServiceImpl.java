package com.bkr.microservice.springmicroservice.services.impl;

import com.bkr.microservice.springmicroservice.Repository.AddressRepository;
import com.bkr.microservice.springmicroservice.Repository.UserRepository;
import com.bkr.microservice.springmicroservice.entities.AddressEntity;
import com.bkr.microservice.springmicroservice.entities.UserEntity;
import com.bkr.microservice.springmicroservice.services.AddressService;
import com.bkr.microservice.springmicroservice.shared.dto.AddressDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<AddressDto> getAllAddresses(String email) {

        List<AddressDto> addressesDto = new ArrayList<>();

        UserEntity currentUser = userRepository.findByEmail(email);

        List<AddressEntity> addresses = currentUser.getAdmin() ==true ? (List<AddressEntity>) addressRepository.findAll() : addressRepository.findByUser(currentUser);

        for(AddressEntity addressEntity : addresses) {
            AddressDto addressDto = new AddressDto();

            ModelMapper modelMapper = new ModelMapper();

            addressDto = modelMapper.map(addressEntity,AddressDto.class);

            addressesDto.add(addressDto);
        }
        return addressesDto;
    }

}
