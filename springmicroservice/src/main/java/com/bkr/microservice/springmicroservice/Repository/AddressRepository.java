package com.bkr.microservice.springmicroservice.Repository;

import com.bkr.microservice.springmicroservice.entities.AddressEntity;
import com.bkr.microservice.springmicroservice.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressEntity,Long> {

   List<AddressEntity> findByUser(UserEntity currentUser);

   AddressEntity findByAddressId(String addressId);

}
