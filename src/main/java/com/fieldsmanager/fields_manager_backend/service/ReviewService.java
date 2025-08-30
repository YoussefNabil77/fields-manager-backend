package com.fieldsmanager.fields_manager_backend.service;

import com.fieldsmanager.fields_manager_backend.dto.ReviewRequest;
import com.fieldsmanager.fields_manager_backend.dto.ReviewResponse;
import com.fieldsmanager.fields_manager_backend.entity.Booking;
import com.fieldsmanager.fields_manager_backend.entity.Review;
import com.fieldsmanager.fields_manager_backend.entity.User;
import com.fieldsmanager.fields_manager_backend.repository.BookingRepository;
import com.fieldsmanager.fields_manager_backend.repository.ReviewRepository;
import com.fieldsmanager.fields_manager_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;


    public ReviewService(ReviewRepository reviewRepository,
                         BookingRepository bookingRepository,
                         UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    public ReviewResponse createReview(ReviewRequest request) {
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));


        Review review = new Review();
        review.setBooking(booking);
        review.setUser(user);
        review.setRating(request.getRating());
        review.setComment(request.getComment());

        Review saved = reviewRepository.save(review);

        return mapToResponse(saved);
    }

    public List<ReviewResponse> getReviewsByBooking(Integer bookingId) {
        return reviewRepository.findByBookingId(bookingId)
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<ReviewResponse> getReviewsByUser(Integer userId) {
        return reviewRepository.findByUserId(userId)
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public ReviewResponse updateReview(Integer reviewId, ReviewRequest request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setRating(request.getRating());
        if (request.getComment() != null) {
            review.setComment(request.getComment());
        }

        if (request.getComment() != null) {
            review.setComment(request.getComment());
        }

        Review updated = reviewRepository.save(review);
        return mapToResponse(updated);
    }


    public void deleteReview(Integer reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        reviewRepository.delete(review);
    }


    private ReviewResponse mapToResponse(Review review) {

        return new ReviewResponse(
                review.getId(),
                review.getBooking().getId(),
                review.getUser().getId(),
                review.getRating(),
                review.getComment()
        );
    }
}
