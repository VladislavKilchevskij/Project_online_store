package com.academy.project.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String manufacturerName;
    @OneToMany(mappedBy = "manufacturer")
    @ToString.Exclude
    private List<Product> products;

}
