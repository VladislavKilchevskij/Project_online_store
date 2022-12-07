package com.academy.project.model.entity;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String productName;
    @Column
    private String modelName;
    @Column
    private String interfaceName;
    @Column
    private Integer frequency;
    @Column
    private Integer memorySize;
    @Column
    private String memoryType;
    @Column
    private Integer memorySlotNumber;
    @Column
    private Integer coreNumber;
    @Column
    private Integer maxThreadNumber;
    @Column
    private String timingNymber;
    @Column
    private String formFactor;
    @Column
    private Integer versionPciExpress;
    @Column
    private BigDecimal price;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Cart> carts;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Media> media;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;
}
