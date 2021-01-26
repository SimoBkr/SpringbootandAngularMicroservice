package com.bkr.microservice.springmicroservice.controllers;

import com.bkr.microservice.springmicroservice.responses.AddressResponse;
import com.bkr.microservice.springmicroservice.services.AddressService;
import com.bkr.microservice.springmicroservice.shared.dto.AddressDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public List<AddressResponse> getAddresses(Principal principal){

        List<AddressResponse> addresses = new ArrayList<>();

        List<AddressDto> addressesDto = addressService.getAllAddresses(principal.getName());

        for(AddressDto addressDto : addressesDto) {

            ModelMapper modelMapper = new ModelMapper();

            AddressResponse addressResponse = modelMapper.map(addressDto,AddressResponse.class);

            addresses.add(addressResponse);
        }

        return addresses;

    }
}
