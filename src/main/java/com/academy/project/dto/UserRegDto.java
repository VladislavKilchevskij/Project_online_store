package com.academy.project.dto;

import lombok.Data;

@Data
public class UserRegDto {
    private String username;
    private String password;
    private String repeatedPassword;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private RoleDto role;
}
