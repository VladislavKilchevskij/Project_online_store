package com.academy.project.service;

import com.academy.project.dto.CategoryDto;
import com.academy.project.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
}