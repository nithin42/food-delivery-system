package com.menu_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//model/Restaurant.java
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantDto {

    private Long id;

    private String name;

    private String address;

    private String phone;

    private String cuisineType;

    private String location;

    private Double rating;

    private Boolean active; 
 
 
 
}