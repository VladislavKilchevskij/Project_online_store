package com.academy.project.mapper;

import com.academy.project.dto.AddressUserIdDto;
import com.academy.project.mapper.defaultmappers.DefaultMapper;
import com.academy.project.model.entity.Address;
import com.academy.project.service.UserService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {UserService.class})
public interface AddressUserIdMapper extends DefaultMapper<AddressUserIdDto, Address> {
    @Mapping(source = "userId", target = "user")
    Address toEntity(AddressUserIdDto addressUserIdDto);
}
