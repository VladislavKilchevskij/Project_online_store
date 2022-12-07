package com.academy.project.service;


import com.academy.project.dto.UserDto;
import com.academy.project.model.entity.User;

public interface UserService {

    void save(UserDto userDto);
    UserDto getUserByUsername(String username);
    User getById(Integer id); // Used by Mapstruct
    UserDto getDtoById(Integer id);
    UserDto findUserInfoByUsernameAndPassword(UserDto userDto);
    void updatePassword(UserDto userDto);
    void updateInfo(UserDto userDto);
}
