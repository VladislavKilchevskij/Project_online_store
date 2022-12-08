package com.academy.project.service.impl;

import com.academy.project.dto.UserDto;
import com.academy.project.dto.UserRegDto;
import com.academy.project.mapper.UserListMapper;
import com.academy.project.mapper.UserMapper;
import com.academy.project.mapper.UserRegMapper;
import com.academy.project.model.entity.User;
import com.academy.project.model.repository.RoleRepository;
import com.academy.project.model.repository.UserRepository;
import com.academy.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String CUSTOMER_ROLE = "ROLE_USER";
    private static final int CUSTOMER_ROLE_ID = 2;
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final UserRegMapper userRegMapper;
    private final UserListMapper userListMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean save(UserRegDto userDto) {
        if(userRepository.existsUserByUsername(userDto.getUsername())) {
            return false;
        }
        User user = userRegMapper.toEntity(userDto);
        user.setRole(roleRepository.getReferenceById(CUSTOMER_ROLE_ID));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return mapper.toDto(userRepository.findByUsername(username));
    }

    @Override
    public User getById(Integer id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public UserDto getDtoById(Integer id) {
        return mapper.toDto(getById(id));
    }

    @Override
    public List<UserDto> getAllCustomers() {
        List<User> users = userRepository.findAll();
        List<User> customers = users.stream()
                .filter(c -> c.getRole().getRoleName().equals(CUSTOMER_ROLE)).toList();
        return userListMapper.toDtoList(customers);
    }

    @Override
    public boolean updatePassword(UserDto userDto) {
        var user = mapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.updatePassword(user);
        return true;
    }

    @Override
    public boolean updateInfo(UserDto userDto) {
        var user = mapper.toEntity(userDto);
        userRepository.updateInfo(user);
        return true;
    }

    @Override
    public boolean changeAccessStatus(Integer customerId) {
        var userCustomer = userRepository.getReferenceById(customerId);
        var accessStatus = (!userCustomer.isAccountNonLocked());
        userCustomer.setAccountNonLocked(accessStatus);
        userRepository.updateAccessStatus(userCustomer);
        return true;
    }
}

