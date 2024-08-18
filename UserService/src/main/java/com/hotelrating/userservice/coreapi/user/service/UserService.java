package com.hotelrating.userservice.coreapi.user.service;

import java.util.List;

import com.hotelrating.userservice.coreapi.user.bo.User;

public interface UserService {

    void saveUser(User user);

    List<User> getAllUser();

    void updateUser(User user);

    User getUserDetails(String userId);

    void removeUser(String userId);

}
