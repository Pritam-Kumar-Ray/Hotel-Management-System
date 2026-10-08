package com.hm.user.service.services;

import com.hm.user.service.entities.UserEntity;

import java.util.List;


public interface UserService {
    UserEntity saveUser(UserEntity user);

    List<UserEntity> getAllUser();

    UserEntity getUser(String userId);

    //TODO: delete and update
}
