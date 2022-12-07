package com.academy.project.service.impl;

import com.academy.project.dto.RoleDto;
import com.academy.project.mapper.RoleListMapper;
import com.academy.project.mapper.RoleMapper;
import com.academy.project.model.repository.RoleRepository;
import com.academy.project.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper mapper;
    private final RoleListMapper listMapper;

    @Override
    public RoleDto findById(Integer id) {
        return mapper.toDto(roleRepository.getReferenceById(id));
    }

    @Override
    public List<RoleDto> findAll() {
        return listMapper.toDtoList(roleRepository.findAll());
    }
}
