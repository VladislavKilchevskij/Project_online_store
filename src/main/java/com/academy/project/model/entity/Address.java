package com.academy.project.model.entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String country;
    @Column
    private String region;
    @Column
    private String district;
    @Column
    private String city;
    @Column
    private String street;
    @Column
    private Integer house;
    @Column
    private String building;
    @Column
    private Integer flat;
    @Column
    private Integer postCode;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
