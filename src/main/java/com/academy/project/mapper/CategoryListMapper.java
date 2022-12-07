package com.academy.project.mapper;

import com.academy.project.dto.CategoryDto;
import com.academy.project.mapper.defaultMappers.DefaultListMapper;
import com.academy.project.model.entity.Category;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = CategoryMapper.class)
public interface CategoryListMapper extends DefaultListMapper<CategoryDto, Category> {
}
