package com.hotelrating.userservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelrating.userservice.coreapi.user.bo.User;
import com.hotelrating.userservice.coreimpl.user.entity.UserDTO;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, String> {

    public List<User> findByName(String Name);

    public Optional<UserDTO> findByEmail(String email);
}
