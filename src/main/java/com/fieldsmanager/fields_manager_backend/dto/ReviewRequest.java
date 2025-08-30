package com.fieldsmanager.fields_manager_backend.dto;

public class ReviewRequest {
    private Integer bookingId;
    private Integer userId;
    private int rating;
    private String comment;

    public ReviewRequest() {
    }

    public ReviewRequest(Integer bookingId, Integer userId, int rating, String comment) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
