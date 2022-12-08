package com.academy.project.service;

import com.academy.project.dto.UserDto;
import com.academy.project.dto.UserRegDto;
import com.academy.project.mapper.*;
import com.academy.project.model.entity.Role;
import com.academy.project.model.entity.User;
import com.academy.project.model.repository.RoleRepository;
import com.academy.project.model.repository.UserRepository;
import com.academy.project.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserServiceImpl userService;

    @BeforeEach
    void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        RoleMapper roleMapper = new RoleMapperImpl();
        UserMapper mapper = new UserMapperImpl(roleMapper, new AddressListMapperImpl(new AddressMapperImpl()));
        UserRegMapper userRegMapper = new UserRegMapperImpl(roleMapper);
        UserListMapper userListMapper = new UserListMapperImpl(mapper);
        roleRepository = Mockito.mock(RoleRepository.class);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userService = new UserServiceImpl(userRepository, mapper, userRegMapper, userListMapper, roleRepository, passwordEncoder);
    }

    @Test
    void saveTest() {
        // Init
        var testDto = new UserRegDto();
        testDto.setUsername("Dima123");
        testDto.setPassword("123qwe");
        var testRole = getRole();

        // Mocks
        Mockito.when(userRepository.existsUserByUsername(testDto.getUsername())).thenReturn(false);
        Mockito.when(roleRepository.getReferenceById(2)).thenReturn(testRole);
        var savedUser = new User();
        savedUser.setUsername("Dima123");
        savedUser.setPassword("123qwe");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(savedUser);

        // Result
        boolean isUserCreated = userService.save(testDto);

        // Assertions
        assertTrue(isUserCreated);
        Mockito.verify(userRepository, Mockito.times(1)).existsUserByUsername(Mockito.any(String.class));
        Mockito.verify(roleRepository, Mockito.times(1)).getReferenceById(Mockito.any(Integer.class));
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    void saveFailedTest() {
        // Init
        var testDto = new UserRegDto();
        testDto.setUsername("Dima123");

        // Mocks
        Mockito.when(userRepository.existsUserByUsername(testDto.getUsername())).thenReturn(true);

        // Result
        boolean isUserCreated = userService.save(testDto);

        // Assertions
        assertFalse(isUserCreated);
        Mockito.verify(userRepository, Mockito.times(1)).existsUserByUsername(Mockito.any(String.class));
        Mockito.verify(roleRepository, Mockito.times(0)).getReferenceById(Mockito.any(Integer.class));
        Mockito.verify(userRepository, Mockito.times(0)).save(Mockito.any(User.class));
    }

    @Test
    void getUserByUsername() {
        // Init
        var dto = new UserDto();
        dto.setUsername("Dima123");
        dto.setPassword("123qwe");

        // Mock
        var returnedUser = new User();
        returnedUser.setUsername("Dima123");
        returnedUser.setPassword("123qwe");
        Mockito.when(userRepository.findByUsername(dto.getUsername())).thenReturn(returnedUser);

        // Result
        var actualUserDto = userService.getUserByUsername(dto.getUsername());

        // Assertions
        assertEquals("Dima123", actualUserDto.getUsername());
        assertEquals("123qwe", actualUserDto.getPassword());
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(Mockito.any(String.class));
    }

    @Test
    void getById() {
        // Init
        var dto = new UserDto();
        dto.setId(1);
        dto.setUsername("Dima123");
        dto.setPassword("123qwe");

        // Mock
        var returnedUser = new User();
        returnedUser.setId(1);
        returnedUser.setUsername("Dima123");
        returnedUser.setPassword("123qwe");
        Mockito.when(userRepository.getReferenceById(dto.getId())).thenReturn(returnedUser);

        // Result
        var actualUser = userService.getById(dto.getId());

        // Assertions
        assertEquals(1, actualUser.getId());
        assertEquals("Dima123", actualUser.getUsername());
        assertEquals("123qwe", actualUser.getPassword());
        Mockito.verify(userRepository, Mockito.times(1)).getReferenceById(Mockito.any(Integer.class));
    }

    @Test
    void getAllCustomers() {
        // Init
        var users = getUsers();

        // Mock
        Mockito.when(userRepository.findAll()).thenReturn(users);

        // Result
        var customers = userService.getAllCustomers();

        // Assertions
        assertEquals(1, customers.size());
        assertEquals("ROLE_USER", customers.get(0).getRole().getRoleName());
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    void updatePassword() {
        // Init
        UserDto userDto = new UserDto();
        userDto.setId(2);
        userDto.setPassword("123qwe");

        // Mock
        var user = new User();
        user.setId(2);
        user.setPassword("qwe123");
        Mockito.doNothing().when(userRepository).updatePassword(Mockito.any(User.class));

        // Result
        var isPasswordUpdated = userService.updatePassword(userDto);

        // Assertions
        assertTrue(isPasswordUpdated);
    }

    @Test
    void changeAccessStatus() {
        // Init
        Integer userId = 3;

        // Mock
        var user = new User();
        user.setId(3);
        user.setAccountNonLocked(true);
        Mockito.when(userRepository.getReferenceById(userId)).thenReturn(user);
        Mockito.doNothing().when(userRepository).updateAccessStatus(user);
        // Result
        var isAccessChanged = userService.changeAccessStatus(userId);

        //Assertions
        assertTrue(isAccessChanged);
        Mockito.verify(userRepository, Mockito.times(1)).getReferenceById(Mockito.any(Integer.class));
        Mockito.verify(userRepository, Mockito.times(1)).updateAccessStatus(Mockito.any(User.class));
    }

    private Role getRole() {
        var role = new Role();
        role.setRoleName("ROLE_USER");
        role.setId(2);
        return role;
    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<>();

        Role adminRole = new Role();
        adminRole.setId(1);
        adminRole.setRoleName("ROLE_ADMIN");

        Role customerRole = new Role();
        customerRole.setId(2);
        customerRole.setRoleName("ROLE_USER");

        User admin = new User();
        admin.setUsername("admin123");
        admin.setRole(adminRole);

        User customer = new User();
        customer.setUsername("customer123");
        customer.setRole(customerRole);

        return List.of(admin, customer);
    }
}