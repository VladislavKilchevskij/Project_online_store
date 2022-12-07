package com.academy.project.service.impl;

import com.academy.project.dto.RoleDto;
import com.academy.project.dto.UserDto;
import com.academy.project.mapper.UserListMapper;
import com.academy.project.mapper.UserMapper;
import com.academy.project.model.entity.User;
import com.academy.project.model.repository.RoleRepository;
import com.academy.project.model.repository.UserRepository;
import com.academy.project.service.RoleService;
import com.academy.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final UserListMapper userListMapper;
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(UserDto userDto) {
        RoleDto roleDto = roleService.findById(2);
        userDto.setRole(roleDto);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(mapper.toEntity(userDto));
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return mapper.toDto(userRepository.findByUsername(username));
    }

    @Override
    public User getById(Integer id) {     //Used by Mapstruct
        return userRepository.getReferenceById(id);
    }

    @Override
    public UserDto getDtoById(Integer id) {
        return mapper.toDto(getById(id));
    }

    @Override
    public UserDto findUserInfoByUsernameAndPassword(UserDto userDto) {
        var user = userRepository.getUserByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
        return mapper.toDto(user);
    }

    @Override
    public List<UserDto> getAllCustomers() {
        var role = roleRepository.findByRoleName("ROLE_USER");
        return userListMapper.toDtoList(userRepository.getAllByRole(role));
    }

    @Override
    public void updatePassword(UserDto userDto) {
        var user = mapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.updatePassword(user);
    }

    @Override
    public void updateInfo(UserDto userDto) {
        var user = mapper.toEntity(userDto);
        userRepository.updateInfo(user);
    }

    @Override
    public void changeAccessStatus(Integer customerId) {
        var userCustomer = userRepository.getReferenceById(customerId);
        var accessStatus = (!userCustomer.isAccountNonLocked());
        userCustomer.setAccountNonLocked(accessStatus);
        userRepository.updateAccessStatus(userCustomer);
    }
}

