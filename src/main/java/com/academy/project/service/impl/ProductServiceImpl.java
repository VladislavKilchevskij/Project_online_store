package com.academy.project.service.impl;

import com.academy.project.dto.ProductDto;
import com.academy.project.mapper.ProductListMapper;
import com.academy.project.mapper.ProductMapper;
import com.academy.project.model.entity.Category;
import com.academy.project.model.entity.Manufacturer;
import com.academy.project.model.entity.Product;
import com.academy.project.model.repository.CategoryRepository;
import com.academy.project.model.repository.ManufacturerRepository;
import com.academy.project.model.repository.ProductRepository;
import com.academy.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;
    private final ProductListMapper mapperList;
    private final ManufacturerRepository manufacturerRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public void save(ProductDto newProductDto) {
        productRepository.save(mapper.toEntity(newProductDto));
    }

    @Override
    public Page<ProductDto> getPreparedPage(int pageNo, String sortField, String sortDir, Integer catId, Integer manId) {
        Page<Product> entities = null;
        int pageSize = 5;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        if (catId != 0 && manId != 0) {
            Manufacturer manufacturer = manufacturerRepository.getReferenceById(manId);
            Category category = categoryRepository.getReferenceById(catId);
            entities = productRepository.findAllByCategoryAndManufacturer(category, manufacturer, pageable);

        } else if (catId == 0 && manId != 0) {
            Manufacturer manufacturer = manufacturerRepository.getReferenceById(manId);
            entities = productRepository.findAllByManufacturer(manufacturer, pageable);
        } else if (manId == 0 && catId != 0) {
            Category category = categoryRepository.getReferenceById(catId);
            entities = productRepository.findAllByCategory(category, pageable);
        } else if (catId == 0 && manId == 0){
            entities = productRepository.findAll(pageable);
        }
        return entities.map(mapper::toDto);
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.getReferenceById(id);
    }

    @Override
    public ProductDto findById(Integer id) {
        return mapper.toDto(productRepository.getReferenceById(id));
    }

    @Override
    public List<ProductDto> findAllProductDto() {
        return mapperList.toDtoList(productRepository.findAll());
    }

    @Override
    public void update(ProductDto editProductDto) {
        var productEntity = mapper.toEntity(editProductDto);
        productRepository.update(productEntity.getId(),
                productEntity.getProductName(),
                productEntity.getModelName(),
                productEntity.getInterfaceName(),
                productEntity.getFrequency(),
                productEntity.getMemorySize(),
                productEntity.getMemoryType(),
                productEntity.getMemorySlotNumber(),
                productEntity.getCoreNumber(),
                productEntity.getMaxThreadNumber(),
                productEntity.getTimingNymber(),
                productEntity.getFormFactor(),
                productEntity.getVersionPciExpress(),
                productEntity.getPrice());
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}
