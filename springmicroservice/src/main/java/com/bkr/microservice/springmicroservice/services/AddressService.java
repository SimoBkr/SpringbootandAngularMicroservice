package com.bkr.microservice.springmicroservice.services;

import com.bkr.microservice.springmicroservice.shared.dto.AddressDto;

import java.util.List;

public interface AddressService {

    List<AddressDto> getAllAddresses(String email);
}
