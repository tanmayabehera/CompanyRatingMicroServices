package com.hotelrating.userservice.coreimpl.user.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelrating.userservice.coreapi.user.bo.User;
import com.hotelrating.userservice.coreimpl.user.service.UserServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserResource {

    private final UserServiceImpl userService;

    public UserResource(UserServiceImpl userService) {
        this.userService = userService;
    }

        @PostMapping
        public ResponseEntity saveUser(@Valid @RequestBody User user) {
            userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUser();
    }

    @PutMapping
    public ResponseEntity updateUser(@Valid @RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        return userService.getUserDetails(userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId) {
        userService.removeUser(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
