package com.bkr.microservice.springmicroservice.services;

import com.bkr.microservice.springmicroservice.shared.dto.AddressDto;

import java.util.List;

public interface AddressService {

    AddressDto createAddress(AddressDto addressDto , String email);
    List<AddressDto> getAllAddresses(String email);
    AddressDto getAllAddresse(String addressId);
    AddressDto updateAddress(String addressId , AddressDto addressDto);
    String deleteAddress(String addressId);

}
