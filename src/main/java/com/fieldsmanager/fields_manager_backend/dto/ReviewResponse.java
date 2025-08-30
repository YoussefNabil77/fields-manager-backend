package com.fieldsmanager.fields_manager_backend.dto;

import java.util.Objects;

public class ReviewResponse {
    private Integer id;
    private Integer bookingId;
    private Integer userId;
    private int rating;
    private String comment;


    public ReviewResponse() {
    }

    public ReviewResponse(Integer id, Integer bookingId, Integer userId, int rating, String comment) {
        this.id = id;
        this.bookingId = bookingId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewResponse)) return false;
        ReviewResponse that = (ReviewResponse) o;
        return rating == that.rating &&
                Objects.equals(id, that.id) &&
                Objects.equals(bookingId, that.bookingId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookingId, userId, rating, comment);
    }


    @Override
    public String toString() {
        return "ReviewResponse{" +
                "id=" + id +
                ", bookingId=" + bookingId +
                ", userId=" + userId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
