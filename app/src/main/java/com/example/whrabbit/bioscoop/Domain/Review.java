package com.example.whrabbit.bioscoop.Domain;

/**
 * Created by Mika Krooswijk on 3-4-2017.
 */

public class Review {

    private String review, customerUsername;
    private int FilmID;
    private float rating;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public int getFilmID() {
        return FilmID;
    }

    public void setFilmID(int filmID) {
        FilmID = filmID;
    }
}
