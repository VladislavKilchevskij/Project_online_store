package com.academy.project.model.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String categoryName;
    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<Product> products;

}
