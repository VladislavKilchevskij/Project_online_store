package com.academy.project.mapper;

import com.academy.project.dto.AddressDto;
import com.academy.project.mapper.defaultMappers.DefaultMapper;
import com.academy.project.model.entity.Address;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AddressMapper extends DefaultMapper<AddressDto, Address> {


}
