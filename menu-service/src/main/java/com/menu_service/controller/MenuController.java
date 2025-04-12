package com.menu_service.controller;

import java.util.List;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menu_service.model.Menu;
import com.menu_service.service.MenuService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/menus")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseEntity<Menu> create(@RequestBody @Valid Menu menu) {
        return new ResponseEntity<>(menuService.create(menu), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Menu> getAll() {
        return menuService.getAll();
    }

    @GetMapping("/{id}")
    public Menu getById(@PathVariable Long id) {
        return menuService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        menuService.delete(id);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<Menu> getByRestaurantId(@PathVariable Long restaurantId) {
        return menuService.getByRestaurantId(restaurantId);
    }
}
