package com.web.dssapp.service;

import com.web.dssapp.dto.UserDto;
import com.web.dssapp.model.User;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface UserService {
    void saveUser(@Valid UserDto user);
    User findByEmail(String email);
    Page<User> findAllUsersP(int pageNumber, int pageSize, Sort sort);
    List<User> findAllUsers();
    Boolean updateUser(User user, UserDto userDto);
    Optional<User> findUserById (int id);
    String deleteMovieById(int id);
}
