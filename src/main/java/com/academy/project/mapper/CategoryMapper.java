package com.academy.project.mapper;

import com.academy.project.dto.CategoryDto;
import com.academy.project.mapper.defaultMappers.DefaultMapper;
import com.academy.project.model.entity.Category;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)

public interface CategoryMapper extends DefaultMapper<CategoryDto, Category> {
}
