package com.academy.project.model.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String mdeiaType;
    @Column
    private String mediaName;
    @Column
    private String mediaUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private Product product;

}
