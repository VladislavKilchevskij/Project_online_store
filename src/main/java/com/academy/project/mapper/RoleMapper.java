package com.academy.project.mapper;

import com.academy.project.dto.RoleDto;
import com.academy.project.mapper.defaultMappers.DefaultMapper;
import com.academy.project.model.entity.Role;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RoleMapper extends DefaultMapper<RoleDto, Role> {
}
