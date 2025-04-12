package com.restaurant_service.controller;

import com.restaurant_service.exception.ResourceNotFoundException;
import com.restaurant_service.model.Restaurant;
import com.restaurant_service.service.RestaurantService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant) {
        Restaurant saved = restaurantService.create(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.get(id);
        if (restaurant == null) {
            throw new ResourceNotFoundException("Restaurant not found with id: " + id);
        }
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {
        List<Restaurant> all = restaurantService.getAll();
        return ResponseEntity.ok(all);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> getByFilters(
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "cuisine", required = false) String cuisine,
            @RequestParam(value = "rating", required = false) Double rating) {

        List<Restaurant> result = restaurantService.getRestaurantsByFilters(location, cuisine, rating);
        return ResponseEntity.ok(result);
    }
}
