package com.example.DiningApi.controller;

import com.example.DiningApi.model.Review;
import com.example.DiningApi.model.Restaurant;
import com.example.DiningApi.model.User;
import com.example.DiningApi.repository.UserRepository;
import com.example.DiningApi.repository.RestaurantRepository;
import com.example.DiningApi.repository.ReviewRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;


import java.util.Optional;

@RequestMapping("/reviews/")
@RestController
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public ReviewController(final ReviewRepository reviewRepository, final RestaurantRepository restaurantRepository, final UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("{reviewId}")
    public Optional<Review> getReview(@PathVariable Long reviewId) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if(reviewOptional.isPresent() == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
        else {
            return reviewOptional;
        }
    }

    @PostMapping()
    public void createReview(@RequestBody Review review) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(review.getRestaurantId());
        if(restaurantOptional.isPresent() == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
        }
        else {
            Optional<User> userOptional = userRepository.findUserByDisplayName(review.getDisplayName());
            if(userOptional.isPresent()==false) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
            else {
                review.setStatus(Review.Status.PENDING);
                reviewRepository.save(review);
            }
        }
    }

    @DeleteMapping("{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if(reviewOptional.isPresent() == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
        else {
            reviewRepository.deleteById(reviewId);
        }
    }







}