package com.academy.project.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private String repeatedPassword;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private RoleDto role;
    private List<AddressDto> addresses;
}
