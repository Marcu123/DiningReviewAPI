package com.example.DiningApi.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.DiningApi.model.Review;
import com.example.DiningApi.model.Review.Status;


import java.util.List;


public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findReviewsByRestaurantIdAndStatus(Long restaurantId, Status status);
    List<Review> findReviewsByStatus(Status status);
}
