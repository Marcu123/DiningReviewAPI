package com.example.DiningApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.DiningApi.model.Restaurant;
import com.example.DiningApi.repository.RestaurantRepository;
import com.example.DiningApi.repository.UserRepository;
import com.example.DiningApi.model.User;
import com.example.DiningApi.model.Review;
import com.example.DiningApi.repository.ReviewRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.lang.Iterable;

@RequestMapping("/restaurants")
@RestController
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public RestaurantController(final RestaurantRepository restaurantRepository, final UserRepository userRepository, final ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    @PostMapping()
    public void createRestaurant(@RequestBody Restaurant restaurant) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findRestaurantsByName(restaurant.getName());
        if(restaurantOptional.isPresent() == true) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Restaurant already exists");
        }
        else {
            restaurantRepository.save(restaurant);
        }
    }

    @GetMapping("/{restaurantId}")
    public Optional<Restaurant> getRestaurant(@PathVariable Long restaurantId){
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if(restaurantOptional.isPresent() == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
        }
        else {
            return restaurantOptional;
        }
    }

    @GetMapping()
    public Iterable<Restaurant> getAllRestaurants(){
        Iterable<Restaurant> restaurantIterable = restaurantRepository.findAll();
        if(restaurantIterable.iterator().hasNext() == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
        }
        else {
            return restaurantIterable;
        }
    }

    @GetMapping("/search/peanutAllergy/{peanutAllergy}")
    public Iterable<Restaurant> getRestaurantByPeanutAllergy(@PathVariable Integer peanutAllergy){
        Iterable<Restaurant> restaurantIterable = restaurantRepository.findRestaurantsByPeanutAllergy(peanutAllergy);
        if(restaurantIterable.iterator().hasNext() == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
        }
        else {
            return restaurantIterable;
        }
    }

    @GetMapping("/search/eggAllergy/{eggAllergy}")
    public Iterable<Restaurant> getRestaurantByEggAllergy(@PathVariable Integer eggAllergy){
        Iterable<Restaurant> restaurantIterable = restaurantRepository.findRestaurantsByEggAllergy(eggAllergy);
        if(restaurantIterable.iterator().hasNext() == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
        }
        else {
            return restaurantIterable;
        }
    }

    @GetMapping("/search/dairyAllergy/{dairyAllergy}")
    public Iterable<Restaurant> getRestaurantByDairyAllergy(@PathVariable Integer dairyAllergy){
        Iterable<Restaurant> restaurantIterable = restaurantRepository.findRestaurantsByDairyAllergy(dairyAllergy);
        if(restaurantIterable.iterator().hasNext() == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
        }
        else {
            return restaurantIterable;
        }
    }
}