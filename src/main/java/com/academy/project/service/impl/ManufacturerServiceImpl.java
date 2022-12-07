package com.academy.project.service.impl;

import com.academy.project.dto.ManufacturerDto;
import com.academy.project.mapper.ManufacturerListMapper;
import com.academy.project.mapper.ManufacturerMapper;
import com.academy.project.model.repository.ManufacturerRepository;
import com.academy.project.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerMapper mapper;
    private final ManufacturerListMapper listMapper;

    @Override
    public List<ManufacturerDto> findAll() {
        return listMapper.toDtoList(manufacturerRepository.findAll());
    }

    @Override
    public ManufacturerDto findById(Integer id) {
        return mapper.toDto(manufacturerRepository.getReferenceById(id));
    }
}
