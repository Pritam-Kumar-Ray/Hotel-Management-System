package com.hm.gateway.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class FallbackController {

    @GetMapping("/fallback/user")
    public ResponseEntity<String> userServiceFallback() {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("USER-SERVICE is currently unavailable. Please try again later.");
    }

    @GetMapping("/fallback/rating")
    public ResponseEntity<String> ratingServiceFallback() {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("RATING-SERVICE is currently unavailable. Please try again later.");
    }

    @GetMapping("/fallback/hotel")
    public ResponseEntity<String> hotelServiceFallback() {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("HOTEL-SERVICE is currently unavailable. Please try again later.");
    }

}
