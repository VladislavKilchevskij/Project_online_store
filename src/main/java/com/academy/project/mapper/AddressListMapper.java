package com.academy.project.mapper;

import com.academy.project.dto.AddressDto;
import com.academy.project.mapper.defaultmappers.DefaultListMapper;
import com.academy.project.model.entity.Address;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = AddressMapper.class)
public interface AddressListMapper extends DefaultListMapper<AddressDto, Address> {

}
