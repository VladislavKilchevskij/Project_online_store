package com.academy.project.service.impl;

import com.academy.project.dto.CategoryDto;
import com.academy.project.mapper.CategoryListMapper;
import com.academy.project.mapper.CategoryMapper;
import com.academy.project.model.repository.CategoryRepository;
import com.academy.project.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;
    private final CategoryListMapper listMapper;

    @Override
    public List<CategoryDto> findAll() {
        return listMapper.toDtoList(categoryRepository.findAll());
    }
}
