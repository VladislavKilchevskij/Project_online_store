package com.academy.project.mapper;


import com.academy.project.dto.UserRegDto;
import com.academy.project.mapper.defaultmappers.DefaultMapper;
import com.academy.project.model.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = RoleMapper.class)
public interface UserRegMapper extends DefaultMapper<UserRegDto, User> {
}
