package com.academy.project.controller;

import com.academy.project.dto.CategoryDto;
import com.academy.project.dto.ManufacturerDto;
import com.academy.project.dto.ProductDto;
import com.academy.project.service.CategoryService;
import com.academy.project.service.ManufacturerService;
import com.academy.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductManagerController {
    private final ManufacturerService manufacturerService;
    private final CategoryService categoryService;
    private final ProductService productService;

    private void initModelAndView(ModelAndView mav) {
        List<ManufacturerDto> manufacturers = manufacturerService.findAll();
        List<CategoryDto> categories = categoryService.findAll();
        mav.addObject("manufacturers", manufacturers);
        mav.addObject("categories", categories);
    }

    @GetMapping("/product_manager/new")
    public ModelAndView addProductForm(ModelAndView mav) {
        initModelAndView(mav);
        mav.addObject("newProductDto", new ProductDto());
        mav.setViewName("addProduct");
        return mav;
    }

    @GetMapping("/product_manager/{id}/edit")
    public ModelAndView editProductForm(@PathVariable Integer id,
                                        ModelAndView mav) {
        initModelAndView(mav);
        var productDto = productService.findById(id);
        mav.addObject("editProductDto", productDto);
        mav.setViewName("editProduct");
        return mav;
    }

    @GetMapping("/product_manager/{id}/delete")
    public String initDeleting(@PathVariable Integer id, Model model) {
        return deleteProduct(id);
    }

    @PostMapping("/product_manager/save")
    public String saveNewProduct(@ModelAttribute("newProductDto") ProductDto newProductDto, Model model) {
        productService.save(newProductDto);
        return "redirect:/catalog";
    }


    @PostMapping("/product_manager/{id}/update")
    public String updateProduct(@ModelAttribute ProductDto editProductDto,
                                @PathVariable Integer id) {
        productService.update(editProductDto);
        return "redirect:/catalog";
    }

    @PostMapping("/product_manager/{id}/delete")
    public String deleteProduct(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/catalog";
    }
}
