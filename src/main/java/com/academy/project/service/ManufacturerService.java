package com.academy.project.service;

import com.academy.project.dto.ManufacturerDto;

import java.util.List;

public interface ManufacturerService {
    List<ManufacturerDto> findAll();
    ManufacturerDto findById(Integer id);
}
