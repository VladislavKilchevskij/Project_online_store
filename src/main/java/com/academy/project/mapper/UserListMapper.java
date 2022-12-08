package com.academy.project.mapper;

import com.academy.project.dto.UserDto;
import com.academy.project.mapper.defaultmappers.DefaultListMapper;
import com.academy.project.model.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = UserMapper.class)
public interface UserListMapper extends DefaultListMapper<UserDto, User> {
}
