package com.hm.user.service.utils;

import com.hm.user.service.dtos.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "RATING-SERVICE")
public interface RatingServiceFeign {

    @PostMapping("/rating-service/ratings")
    public Rating creteRating(@RequestBody Rating rating);

    @PutMapping("/rating-service/ratings/{ratingId}")
    public Rating updateRating(@PathVariable String ratingId, @RequestBody Rating rating);
}
