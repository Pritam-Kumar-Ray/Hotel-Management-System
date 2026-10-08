package com.hm.user.service.utils;

import com.hm.user.service.dtos.Hotel;
import com.hm.user.service.utils.fallbacks.HotelCLientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Fallback is for the circuit breakers
@FeignClient(name = "HOTEL-SERVICE", fallback = HotelCLientFallback.class)
public interface HotelServiceFeign {

    @GetMapping("/hotel-service/hotels/{hotelId}")
    public Hotel getHotel(@PathVariable String hotelId);
}
