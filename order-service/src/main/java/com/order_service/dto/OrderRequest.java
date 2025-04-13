package com.order_service.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Long menuId;
    private int quantity;
}
