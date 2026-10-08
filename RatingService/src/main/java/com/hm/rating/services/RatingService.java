package com.hm.rating.services;


import com.hm.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    public Rating createRating(Rating rating);

    public List<Rating> getAllRatings();

    public List<Rating> getAllRatingsByUserId(String userId);

    public List<Rating> getAllRatingsByHotelId(String hotelId);
}
