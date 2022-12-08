package com.academy.project.service;


import com.academy.project.dto.UserDto;
import com.academy.project.dto.UserRegDto;
import com.academy.project.model.entity.User;

import java.util.List;

public interface UserService {

    boolean save(UserRegDto userDto);
    UserDto getUserByUsername(String username);
    User getById(Integer id); // Used by Mapstruct
    UserDto getDtoById(Integer id);

    List<UserDto> getAllCustomers();

    boolean updatePassword(UserDto userDto);
    boolean updateInfo(UserDto userDto);

    boolean changeAccessStatus(Integer customerId);
}
