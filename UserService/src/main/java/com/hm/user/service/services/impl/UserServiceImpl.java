package com.hm.user.service.services.impl;

import com.hm.user.service.dtos.Hotel;
import com.hm.user.service.dtos.Rating;
import com.hm.user.service.entities.UserEntity;
import com.hm.user.service.exceptions.ResourceNotFoundException;
import com.hm.user.service.repositoties.UserRepositories;
import com.hm.user.service.services.UserService;
import com.hm.user.service.utils.HotelServiceFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RefreshScope
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositories userRepositories;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelServiceFeign hotelServiceFeign;
    @Value("${app.description:My description}")
    private String appDescription;

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
//    private final String ratingUrl = "http://localhost:8083/rating-service";
    @Value("${services.urls.rating-service:}")
    private String ratingUrl;
    @Value("${services.urls.hotel-service:}")
    private String hotelUrl;

    @Override
    public UserEntity saveUser(UserEntity user) {
        String randonUserId = UUID.randomUUID().toString();
        user.setUserId(randonUserId);
        return userRepositories.save(user);
    }

    @Override
    public List<UserEntity> getAllUser() {
        log.info("The git config refresh check: {}", appDescription);
        return userRepositories.findAll();
    }

    @Override
    public UserEntity getUser(String userId) {
        UserEntity user = userRepositories.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User with given id: "+ userId+" not found in the server"));

        // http://localhost:8083/rating-service/ratings/users/14ac26f3-e94b-4e3c-9502-278c9c5b7efc
        String ratingUrl1 = ratingUrl+"/ratings/users/"+userId;
        log.info("ratingUrl1 is: {}", ratingUrl1);

        ResponseEntity<List<Rating>> response = restTemplate.exchange(
                ratingUrl1,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Rating>>() {}
        );

//        ArrayList<Rating> ratings = restTemplate.getForObject(url, ArrayList.class);

        List<Rating> ratings = response.getBody();


        assert ratings != null;

        List<Rating> ratingList= ratings.stream().map(rating ->
        {
//            http://localhost:8082/hotel-service/hotels/e2461ac5-029c-40b4-b3bf-6c972bec5c73

//            Removing this line to implement the feign client
            /*
            String hotelUrl1 = hotelUrl+"/hotels/"+rating.getHotelId();
            ResponseEntity<Hotel> hotelResponseEntity =  restTemplate.exchange(hotelUrl1, HttpMethod.GET, null, Hotel.class);
            Hotel hotel = hotelResponseEntity.getBody();
            */
            // Calling feign client
            Hotel hotel = hotelServiceFeign.getHotel(rating.getHotelId());
            log.info("UserserviceImpl :: hotel :: {}", hotel);
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        log.info("The ratingList response is: {}", ratingList);
        return user;
    }
}
