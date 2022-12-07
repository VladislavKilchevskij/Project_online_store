package com.academy.project.service.impl;

import com.academy.project.dto.AddressDto;
import com.academy.project.dto.AddressUserIdDto;
import com.academy.project.mapper.AddressListMapper;
import com.academy.project.mapper.AddressMapper;
import com.academy.project.mapper.AddressUserIdMapper;
import com.academy.project.model.entity.Address;
import com.academy.project.model.repository.AddressRepository;
import com.academy.project.model.repository.UserRepository;
import com.academy.project.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AddressUserIdMapper addressUserIdMapper;
    private final AddressMapper mapper;
    private final AddressListMapper listMapper;

    @Override
    public void save(AddressUserIdDto addressUserIdDto, Integer userId) {
        addressUserIdDto.setUserId(userId);
        addressRepository.save(addressUserIdMapper.toEntity(addressUserIdDto));
    }

    @Override
    public void update(AddressDto updateAddressDto) {
        var addressEntity = mapper.toEntity(updateAddressDto);
        addressRepository.update(addressEntity);
    }

    @Override
    public Address getById(Integer id) {
        return addressRepository.getReferenceById(id);
    }

    @Override
    public AddressDto findById(Integer id) {
        return mapper.toDto(addressRepository.getReferenceById(id));
    }

    @Override
    public List<AddressDto> findAll() {
        return listMapper.toDtoList(addressRepository.findAll());
    }

    @Override
    public List<AddressDto> findAllByUserId(Integer userId) {
        var user = userRepository.getReferenceById(userId);
        return listMapper.toDtoList(user.getAddresses());
    }

    @Override
    public void delete(Integer id) {
        addressRepository.deleteById(id);
    }
}
