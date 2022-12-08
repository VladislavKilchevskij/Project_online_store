package com.academy.project.service;

import com.academy.project.dto.AddressDto;
import com.academy.project.dto.AddressUserIdDto;
import com.academy.project.model.entity.Address;

import java.util.List;

public interface AddressService {

    boolean save(AddressUserIdDto addressUserIdDto, Integer userId);

    boolean update(AddressDto updateAddressDto);

    Address getById(Integer id);
    AddressDto findById(Integer id);
    List<AddressDto> findAll();
    List<AddressDto> findAllByUserId(Integer userId);

    boolean delete(Integer id);
}
