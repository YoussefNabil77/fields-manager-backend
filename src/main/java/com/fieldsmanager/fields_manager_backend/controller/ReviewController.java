package com.fieldsmanager.fields_manager_backend.controller;

import com.fieldsmanager.fields_manager_backend.dto.ReviewRequest;
import com.fieldsmanager.fields_manager_backend.dto.ReviewResponse;
import com.fieldsmanager.fields_manager_backend.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;


    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.createReview(request));
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByBooking(@PathVariable Integer bookingId) {
        return ResponseEntity.ok(reviewService.getReviewsByBooking(bookingId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(reviewService.getReviewsByUser(userId));
    }


    @PutMapping("/update/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable Integer reviewId,
            @RequestBody ReviewRequest request
    ) {
        return ResponseEntity.ok(reviewService.updateReview(reviewId, request));
    }


    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}
