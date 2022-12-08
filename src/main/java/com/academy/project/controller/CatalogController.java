package com.academy.project.controller;

import com.academy.project.dto.*;
import com.academy.project.service.CategoryService;
import com.academy.project.service.ManufacturerService;
import com.academy.project.service.ProductService;
import com.academy.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CatalogController {
    private static final String SORTFIELD = "productName";
    private static final String ASC = "asc";
    private static final String DESC = "desc";

    private final UserService userService;
    private final ProductService productService;
    private final ManufacturerService manufacturerService;
    private final CategoryService categoryService;

    @GetMapping("/catalog")
    public String initCatalog(Authentication authentication,
                              @RequestParam(value = "catId", required = false) Integer catId,
                              @RequestParam(value = "manId", required = false) Integer manId,
                              Model model) {
        var userId = userService.getUserByUsername(authentication.getName()).getId();
        model.addAttribute("userId", userId);
        if (catId != null || manId != null) {
            return getPaginatedCatalog(userId,
                    1, SORTFIELD, ASC, catId, manId, model);
        }
        return getPaginatedCatalog(userId,
                1, SORTFIELD, ASC, 0, 0, model);
    }

    @GetMapping("/catalog/{userId}/page/{pageNo}")
    public String getPaginatedCatalog(@PathVariable Integer userId,
                                      @PathVariable(value = "pageNo") int pageNo,
                                      @RequestParam("sortField") String sortField,
                                      @RequestParam("sortDir") String sortDir,
                                      @RequestParam(value = "catId", required = false) Integer catId,
                                      @RequestParam(value = "manId", required = false) Integer manId,
                                      Model model) {
        if (catId == null || manId == null) {
            catId = 0;
            manId = 0;
        }
        Page<ProductDto> page = productService.getPreparedPage(pageNo, sortField, sortDir, catId, manId);
        List<ManufacturerDto> listManufacturers = manufacturerService.findAll();
        List<CategoryDto> listCategories = categoryService.findAll();
        List<ProductDto> listProducts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals(ASC) ? DESC : ASC);
        model.addAttribute("catId", catId);
        model.addAttribute("manId", manId);
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("listManufacturers", listManufacturers);
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("productElement", new ProductDto());
        model.addAttribute("cartDto", new CartDto());
        return "catalog";
    }

    @GetMapping("/catalog/{userId}/product/{id}")
    public String getProductInfo(@PathVariable Integer userId,
                                 @PathVariable Integer id,
                                 Model model) {
        var productDto = productService.findById(id);
        model.addAttribute("product", productDto);
        model.addAttribute("mediaElement", new MediaDto());
        return "product";
    }
}
