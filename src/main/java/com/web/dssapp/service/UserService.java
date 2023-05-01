package com.web.dssapp.service;

import com.web.dssapp.dto.UserDto;
import com.web.dssapp.model.User;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(@Valid UserDto user);
    User findByusername(String username);
    User findByEmail(String email);
    //Page<User> findAllUsersP(int pageNumber, int pageSize, Sort sort);
    //List<User> findAllUsers();
    Boolean updateUser(User user, UserDto userDto);
    Optional<User> findUserById (int id);
    String deleteUserById(int id);
    public List<User> findUsersByRole(String role);
    public int maxid();
    
}
