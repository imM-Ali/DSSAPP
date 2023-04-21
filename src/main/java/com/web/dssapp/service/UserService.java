package com.web.dssapp.service;

import com.web.dssapp.dto.UserDto;
import com.web.dssapp.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
