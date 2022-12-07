package com.academy.project.service;


import com.academy.project.dto.UserDto;
import com.academy.project.model.entity.User;

import java.util.List;

public interface UserService {

    void save(UserDto userDto);
    UserDto getUserByUsername(String username);
    User getById(Integer id); // Used by Mapstruct
    UserDto getDtoById(Integer id);
    UserDto findUserInfoByUsernameAndPassword(UserDto userDto);

    List<UserDto> getAllCustomers();

    void updatePassword(UserDto userDto);
    void updateInfo(UserDto userDto);

    void changeAccessStatus(Integer customerId);
}
