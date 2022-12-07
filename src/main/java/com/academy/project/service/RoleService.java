package com.academy.project.service;

import com.academy.project.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto findById(Integer id);
    List<RoleDto> findAll();
}
