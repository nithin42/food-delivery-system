package com.order_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order_service.dto.MenuDto;

@FeignClient(name = "menu-service", url = "http://localhost:8082")
public interface MenuClient {
    @GetMapping("/menus/{id}")
    MenuDto getMenuById(@PathVariable("id") Long id);
}
