package com.example.praca_magisterska.repository;

import com.example.praca_magisterska.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRespository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}
