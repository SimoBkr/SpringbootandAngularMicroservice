package com.bkr.microservice.springmicroservice.services.impl;

import com.bkr.microservice.springmicroservice.Repository.AddressRepository;
import com.bkr.microservice.springmicroservice.Repository.UserRepository;
import com.bkr.microservice.springmicroservice.entities.AddressEntity;
import com.bkr.microservice.springmicroservice.entities.UserEntity;
import com.bkr.microservice.springmicroservice.services.AddressService;
import com.bkr.microservice.springmicroservice.shared.dto.AddressDto;
import com.bkr.microservice.springmicroservice.shared.dto.UserDto;
import com.bkr.microservice.springmicroservice.shared.dto.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Override
    public AddressDto createAddress(AddressDto addressDto , String email) {

        UserEntity currentUser = userRepository.findByEmail(email);

        addressDto.setAddressId(utils.generateStringId(32));

        ModelMapper modelMapper = new ModelMapper();

        AddressEntity addressEntity =modelMapper.map(addressDto,AddressEntity.class);

        addressRepository.save(addressEntity);

        AddressDto address = modelMapper.map(addressEntity,AddressDto.class);

        return address;
    }

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

    @Override
    public AddressDto getAllAddresse(String addressId) {


            AddressDto addressDto = new AddressDto();

            AddressEntity addresses = addressRepository.findByAddressId(addressId);
            ModelMapper modelMapper = new ModelMapper();

             addressDto = modelMapper.map(addresses,AddressDto.class);

        return addressDto;
    }

    @Override
    public AddressDto updateAddress(String addressId, AddressDto addressDto) {

        AddressEntity addressEntity = addressRepository.findByAddressId(addressId);

        if(addressEntity == null) throw new UsernameNotFoundException(addressId);

        addressEntity.setCity(addressDto.getCity());
        addressEntity.setCountry(addressDto.getCountry());
        addressEntity.setPostal(addressDto.getPostal());
        addressEntity.setStreet(addressDto.getStreet());
        addressEntity.setType(addressDto.getType());

        addressRepository.save(addressEntity);

        AddressDto addressUpdated = new AddressDto();
        BeanUtils.copyProperties(addressEntity,addressUpdated);

        return addressUpdated;
    }

    @Override
    public String deleteAddress(String addressId) {

        AddressEntity address = addressRepository.findByAddressId(addressId);

        if(address == null) throw new RuntimeException("Address not found");

        addressRepository.delete(address);

        if(addressRepository.findByAddressId(addressId) != null) {
            System.out.println("Hi Hi I'm not deleted");
        }else{
            System.out.println("Ohhh Sheet Good By i'm dead!! ");
        }

        return "Address was Deleted";

    }

}
