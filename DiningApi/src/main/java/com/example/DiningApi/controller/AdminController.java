package com.example.DiningApi.controller;

import com.example.DiningApi.model.Review;
import com.example.DiningApi.model.Review.Status;
import com.example.DiningApi.repository.ReviewRepository;
import com.example.DiningApi.repository.RestaurantRepository;
import com.example.DiningApi.repository.UserRepository;
import com.example.DiningApi.model.Restaurant;
import com.example.DiningApi.model.User;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.DiningApi.model.Admin;

import java.util.Optional;
import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminController {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public AdminController(final ReviewRepository reviewRepository, final RestaurantRepository restaurantRepository, final UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/reviews")
    public List<Review> getReviewsByStatus(@RequestParam Status status) {
        return reviewRepository.findReviewsByStatus(status);
    }

    @PutMapping("/review/{reviewId}")
    public void updateReview(@PathVariable Long reviewId, @RequestBody Admin admin) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if(reviewOptional.isPresent() == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
        else {
            Review review = reviewOptional.get();
            Optional <Restaurant> restaurantOptional = restaurantRepository.findById(review.getRestaurantId());
            if(admin.getAccept() == true) {
                review.setStatus(Review.Status.ACCEPTED);
                reviewRepository.save(review);
                overallScoring(restaurantOptional.get());

            }
            else {
                review.setStatus(Review.Status.REJECTED);
                reviewRepository.save(review);
                overallScoring(restaurantOptional.get());
            }
        }
    }

    public void overallScoring(Restaurant restaurant) {
        List<Review> reviews = reviewRepository.findReviewsByRestaurantIdAndStatus(restaurant.getId(), Review.Status.ACCEPTED);
        if(reviews.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No reviews found");
        }
        else {
            int overallScore = 0;
            int egg=0;
            int diary=0;
            int peanut=0;
            for(Review review : reviews) {
                overallScore += review.getScore();
                egg+=review.getEggScore();
                diary+=review.getDairyScore();
                peanut+=review.getPeanutScore();
            }
            restaurant.setOverallRating(overallScore/reviews.size());
            restaurant.setEggAllergy(egg/reviews.size());
            restaurant.setDairyAllergy(diary/reviews.size());
            restaurant.setPeanutAllergy(peanut/reviews.size());
            restaurantRepository.save(restaurant);

        }
    }

}