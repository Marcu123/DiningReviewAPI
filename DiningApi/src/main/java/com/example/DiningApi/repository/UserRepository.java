package com.example.DiningApi.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.DiningApi.model.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByDisplayName(String displayName);
}