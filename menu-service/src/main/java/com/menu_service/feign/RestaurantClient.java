package com.menu_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.menu_service.dto.RestaurantDto;

@FeignClient(name = "restaurant-service", url = "http://localhost:8081")
public interface RestaurantClient {

    @GetMapping("/restaurants/{id}")
    RestaurantDto getRestaurantById(@PathVariable("id") Long id);
}
