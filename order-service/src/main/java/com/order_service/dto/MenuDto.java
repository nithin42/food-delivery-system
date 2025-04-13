package com.order_service.dto;

import lombok.Data;

@Data
public class MenuDto {
    private Long id;
    private String itemName;
    private double price;
}
