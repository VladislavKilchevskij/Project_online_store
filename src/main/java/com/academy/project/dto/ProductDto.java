package com.academy.project.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto {

    private Integer id;
    private String productName;
    private CategoryDto category;
    private ManufacturerDto manufacturer;
    private String modelName;
    private String interfaceName;
    private Integer frequency;
    private Integer memorySize;
    private String memoryType;
    private Integer memorySlotNumber;
    private Integer coreNumber;
    private Integer maxThreadNumber;
    private String timingNymber;
    private String formFactor;
    private Integer versionPciExpress;
    private BigDecimal price;
    private List<MediaDto> media;
}
