package com.academy.project.service;

import com.academy.project.dto.ProductDto;
import com.academy.project.model.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    void save(ProductDto newProductDto);
    Product getProductById(Integer id);

    Page<ProductDto> getPreparedPage(int pageNo, String sortField, String sortDir, Integer catId, Integer manId);
    List<ProductDto> findAllProductDto();
    ProductDto findById(Integer id);
    void update(ProductDto editProductDto);
    void delete(Integer id);


}
