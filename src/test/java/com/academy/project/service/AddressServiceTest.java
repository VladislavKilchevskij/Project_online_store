package com.academy.project.service;

import com.academy.project.dto.AddressDto;
import com.academy.project.dto.AddressUserIdDto;
import com.academy.project.mapper.*;
import com.academy.project.model.entity.Address;
import com.academy.project.model.entity.User;
import com.academy.project.model.repository.AddressRepository;
import com.academy.project.model.repository.UserRepository;
import com.academy.project.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AddressServiceTest {

    private AddressRepository addressRepository;
    private AddressUserIdMapper addressUserIdMapper;
    private AddressMapper mapper;
    private AddressService addressService;

    @BeforeEach
    void setup() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        addressRepository = Mockito.mock(AddressRepository.class);
        addressUserIdMapper = Mockito.mock(AddressUserIdMapper.class);
        mapper = Mockito.mock(AddressMapper.class);
        AddressListMapper listMapper = Mockito.mock(AddressListMapper.class);
        addressService = new AddressServiceImpl(userRepository, addressRepository, addressUserIdMapper, mapper, listMapper);
    }

    @Test
    void save() {
        // Init
        var testDto = new AddressUserIdDto();
        Integer userId = 3;
        testDto.setCity("Test");

        // Mocks
        var entity = new Address();
        entity.setCity("Test");
        var user = new User();
        user.setId(3);
        entity.setUser(user);
        Mockito.when(addressUserIdMapper.toEntity(testDto)).thenReturn(entity);
        Mockito.when(addressRepository.save(Mockito.any(Address.class))).thenReturn(entity);

        // Result
        boolean isAddressSaved = addressService.save(testDto, userId);

        // Assertions
        assertTrue(isAddressSaved);
        Mockito.verify(addressUserIdMapper, Mockito.times(1)).toEntity(Mockito.any(AddressUserIdDto.class));
        Mockito.verify(addressRepository, Mockito.times(1)).save(Mockito.any(Address.class));
    }

    @Test
    void update() {
        // Init
        var testDto = new AddressDto();
        testDto.setId(1);
        testDto.setCity("Test");

        // Mock
        var entity = new Address();
        entity.setId(1);
        entity.setCity("Test");
        Mockito.when(mapper.toEntity(testDto)).thenReturn(entity);
        Mockito.doNothing().when(addressRepository).update(entity);

        // Result
        var isUpdated = addressService.update(testDto);

        // Assertions
        assertTrue(isUpdated);
        Mockito.verify(mapper, Mockito.times(1)).toEntity(Mockito.any(AddressDto.class));
        Mockito.verify(addressRepository, Mockito.times(1)).update(Mockito.any(Address.class));
    }

    @Test
    void delete() {
        // Init
        Integer id = 1;

        //Mock
        Mockito.doNothing().when(addressRepository).deleteById(id);

        // Result
        var isDeleted = addressService.delete(id);

        // Assertions
        assertTrue(isDeleted);
        Mockito.verify(addressRepository, Mockito.times(1)).deleteById(Mockito.any(Integer.class));
    }
}