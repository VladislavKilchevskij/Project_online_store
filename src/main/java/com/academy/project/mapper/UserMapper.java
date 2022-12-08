package com.academy.project.mapper;

import com.academy.project.dto.UserDto;
import com.academy.project.mapper.defaultmappers.DefaultMapper;
import com.academy.project.model.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {RoleMapper.class, AddressListMapper.class})
public interface UserMapper extends DefaultMapper<UserDto, User> {
    @Mapping(target = "repeatedPassword", ignore = true)
    UserDto toDto (User user);
}
