package com.hm.user.service.utils.fallbacks;

import com.hm.user.service.dtos.Hotel;
import com.hm.user.service.utils.HotelServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HotelCLientFallback implements HotelServiceFeign {
    @Override
    public Hotel getHotel(String hotelId) {
        log.info("Hotel is in fallback method");
        return Hotel.builder().about("This is under fallback").build();
    }
}
