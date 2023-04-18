package com.fundatec.tcc.repository;

import com.fundatec.tcc.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
}

