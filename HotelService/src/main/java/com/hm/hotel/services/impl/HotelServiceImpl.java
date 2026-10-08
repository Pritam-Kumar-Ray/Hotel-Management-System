package com.hm.hotel.services.impl;

import com.hm.hotel.entities.Hotel;
import com.hm.hotel.exceptions.ResourceNotFoundException;
import com.hm.hotel.repositories.HotelRepository;
import com.hm.hotel.services.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel createHotel(Hotel hotel) {
        String randomId = UUID.randomUUID().toString();
        hotel.setId(randomId);
        Hotel hotel1 = hotelRepository.save(hotel);
        return hotel1;
    }

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String id) throws InterruptedException {
        log.info("Inside getHotel :: id: {}", id);
        Thread.sleep(5000);

        /*log.info("This is Retrying");
        if(1<5){
            throw new NullPointerException("Retrying for null parameter.....");
        }*/
        return hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id is not found: "+id));
    }
}
