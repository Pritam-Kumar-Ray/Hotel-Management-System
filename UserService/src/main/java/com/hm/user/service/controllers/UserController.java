package com.hm.user.service.controllers;

import com.hm.user.service.entities.UserEntity;
import com.hm.user.service.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user){
        UserEntity userEntity = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUser(@PathVariable("userId") String userId){
        /*log.info("This is IN USER Retrying");
        if(1<5){
            throw new NullPointerException("User:: Retrying for null parameter.....");
        }*/
        UserEntity userEntity = userService.getUser(userId);
        return ResponseEntity.ok(userEntity);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUser(){
        List<UserEntity> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}
