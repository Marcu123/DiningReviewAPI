package com.example.DiningApi.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.DiningApi.repository.UserRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.example.DiningApi.model.User;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{displayName}")

    public Optional<User> getUserDisplayName(@PathVariable String displayName) {

        Optional<User> userOptional = userRepository.findUserByDisplayName(displayName);
        if(userOptional.isPresent() == false) {
            System.out.println("User not found for displayName: " + displayName);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        else {
            System.out.println("User found for displayName: " + displayName);
            return userOptional;
        }
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        Optional<User> userOptional = userRepository.findUserByDisplayName(user.getDisplayName());
        if(userOptional.isPresent() == true) {
            System.out.println("User already exists for displayName: " + user.getDisplayName());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }
        return userRepository.save(user);
    }

    @PutMapping("/{displayName}")
    public User updateUser(@PathVariable String displayName, @RequestBody User u) {
        Optional<User> user = userRepository.findUserByDisplayName(displayName);
        if(user.isPresent() == false) {
            System.out.println("User not found for displayName: " + displayName);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        if(u.getDisplayName() != null) {
            user.get().setDisplayName(u.getDisplayName());
        }

        if(u.getCity() != null) {
            user.get().setCity(u.getCity());
        }

        if(u.getState() != null) {
            user.get().setState(u.getState());
        }

        if(u.getZipcode() != null) {
            user.get().setZipcode(u.getZipcode());
        }

        if(u.getPeanutInt() != null) {
            user.get().setPeanutInt(u.getPeanutInt());
        }

        if(u.getEggInt() != null) {
            user.get().setEggInt(u.getEggInt());
        }

        if(u.getDairyInt() != null) {
            user.get().setDairyInt(u.getDairyInt());
        }

        return userRepository.save(user.get());
    }

    @DeleteMapping("/{displayName}")
    public void deleteUser(@PathVariable String displayName) {
        Optional<User> userOptional = userRepository.findUserByDisplayName(displayName);
        if(userOptional.isPresent() == false) {
            System.out.println("User not found for displayName: " + displayName);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        else {
            userRepository.delete(userOptional.get());
        }
    }
}

