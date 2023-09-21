package com.example.DiningApi.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.DiningApi.model.Restaurant;

import java.util.List;
import java.util.Optional;


public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Optional<Restaurant> findRestaurantsByName(String name);
    List<Restaurant> findRestaurantsByPeanutAllergy(Integer peanutAllergy);
    List<Restaurant> findRestaurantsByEggAllergy(Integer eggAllergy);
    List<Restaurant> findRestaurantsByDairyAllergy(Integer dairyAllergy);
}