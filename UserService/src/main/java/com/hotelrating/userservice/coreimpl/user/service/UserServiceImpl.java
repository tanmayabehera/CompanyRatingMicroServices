package com.hotelrating.userservice.coreimpl.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hotelrating.userservice.coreapi.user.bo.User;
import com.hotelrating.userservice.coreapi.user.service.UserService;
import com.hotelrating.userservice.coreimpl.user.entity.UserDTO;
import com.hotelrating.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(convertUserBOToDTO(user));
    }


    @Override
    public List<User> getAllUser() {
        return convertUserDTOToBo(userRepository.findAll());
    }

    @Override
    public void updateUser(User user) {
        Optional<UserDTO> userDTO = userRepository.findById(user.getId());
        if (!userDTO.isPresent()) {
            throw new IllegalArgumentException("User is not available");
        }
        UserDTO userDTO1 = convertUserBOToDTO(user);
        userDTO1.setId(user.getId());
        userRepository.save(userDTO1);
    }

    @Override
    public User getUserDetails(String userId) {
        Optional<UserDTO> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new IllegalArgumentException("User is not available");
        }
        return convertDTOToUserBO(user.get());
    }

    @Override
    public void removeUser(String userId) {
        Optional<UserDTO> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new IllegalArgumentException("User is not available");
        }
        userRepository.deleteById(userId);
    }

    private UserDTO convertUserBOToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    private User convertDTOToUserBO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    private List<User> convertUserDTOToBo(List<UserDTO> userDTOList) {
        ArrayList<User> users = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userDTOList)) {
            userDTOList.forEach(x -> {
                User user = new User();
                user.setId(x.getId());
                user.setName(x.getName());
                user.setEmail(x.getEmail());
                user.setPassword(x.getPassword());
                users.add(user);
            });
        }
        return users;
    }
}

