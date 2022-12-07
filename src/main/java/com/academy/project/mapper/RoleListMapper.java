package com.academy.project.mapper;

import com.academy.project.dto.RoleDto;
import com.academy.project.mapper.defaultMappers.DefaultListMapper;
import com.academy.project.model.entity.Role;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = RoleMapper.class)
public interface RoleListMapper extends DefaultListMapper<RoleDto, Role> {
}
