package com.hm.hotel.services;

import com.hm.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    public Hotel createHotel(Hotel hotel);
    public List<Hotel> getAllHotel();
    Hotel getHotel(String id) throws InterruptedException;
}
