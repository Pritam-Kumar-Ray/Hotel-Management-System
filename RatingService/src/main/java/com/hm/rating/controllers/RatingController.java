package com.hm.rating.controllers;

import com.hm.rating.entities.Rating;
import com.hm.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

//    @Autowired
//    RestClient restClient;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        /*ResponseDTO responseDTO = restClient.post()
                .uri("abc")
                .body(requestDTO)
                .header("name", "pritam")
                .header("roll", "12")
                .retrieve()
                .body(ResponseDTO.class);*/
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings(){
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingsByUserId(@PathVariable String userId){
        return ResponseEntity.ok(ratingService.getAllRatingsByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingsByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingService.getAllRatingsByHotelId(hotelId));
    }
}
