package com.academy.project.service;

import com.academy.project.dto.AddressDto;
import com.academy.project.dto.AddressUserIdDto;
import com.academy.project.model.entity.Address;

import java.util.List;

public interface AddressService {

    void save(AddressUserIdDto addressUserIdDto, Integer userId);

    void update(AddressDto updateAddressDto);

    Address getById(Integer id);        // Used by Mapstruct
    AddressDto findById(Integer id);
    List<AddressDto> findAll();
    List<AddressDto> findAllByUserId(Integer userId);

    void delete(Integer id);
}
