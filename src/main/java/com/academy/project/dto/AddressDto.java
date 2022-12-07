package com.academy.project.dto;

import lombok.Data;


@Data
public class AddressDto {
    private Integer id;
    private String country;
    private String region;
    private String district;
    private String city;
    private String street;
    private Integer house;
    private String building;
    private Integer flat;
    private Integer postCode;
}
