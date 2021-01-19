package com.bkr.microservice.springmicroservice.Repository;

import com.bkr.microservice.springmicroservice.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity,Long> {

    UserEntity findByEmail(String email);

    UserEntity findByUserId(String userId);


}
