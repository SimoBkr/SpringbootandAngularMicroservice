package com.bkr.microservice.springmicroservice.controllers;

import com.bkr.microservice.springmicroservice.requests.AddressRequest;
import com.bkr.microservice.springmicroservice.requests.UserRequest;
import com.bkr.microservice.springmicroservice.responses.AddressResponse;
import com.bkr.microservice.springmicroservice.responses.UserResponse;
import com.bkr.microservice.springmicroservice.services.AddressService;
import com.bkr.microservice.springmicroservice.shared.dto.AddressDto;
import com.bkr.microservice.springmicroservice.shared.dto.UserDto;
import org.apache.tomcat.jni.Address;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping(path= "/{addressId}")
    public ResponseEntity<AddressResponse> getAddresse(@PathVariable String addressId){

            AddressDto addressesDto = addressService.getAllAddresse(addressId);
            ModelMapper modelMapper = new ModelMapper();
            AddressResponse addressResponse = modelMapper.map(addressesDto,AddressResponse.class);

        return new ResponseEntity<AddressResponse>(addressResponse,HttpStatus.OK);
    }

    @PutMapping(path = "/{addressId}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable String addressId , @RequestBody AddressRequest addressRequest) {

        AddressDto addressDto = addressService.getAllAddresse(addressId);

        BeanUtils.copyProperties(addressRequest,addressDto);

        AddressDto updateAddress = addressService.updateAddress(addressId ,addressDto);
        AddressResponse addressResponse = new AddressResponse();
        BeanUtils.copyProperties(updateAddress,addressResponse);

        return new ResponseEntity<AddressResponse>(addressResponse,HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<AddressResponse> createAddress(@RequestBody @Valid AddressRequest addressRequest , Principal principal) {

        AddressDto addressDto = new AddressDto();

        ModelMapper modelMapper = new ModelMapper();

        addressDto =  modelMapper.map(addressRequest,AddressDto.class);

        AddressDto createAddress = addressService.createAddress(addressDto ,principal.getName());

        AddressResponse addressResponse = modelMapper.map(createAddress,AddressResponse.class);

        return new ResponseEntity<AddressResponse>(addressResponse,HttpStatus.CREATED);

    }

    @DeleteMapping("/{addressId}")
    public String deleteAddress(@PathVariable String addressId) {

        return addressService.deleteAddress(addressId);
    }

}
