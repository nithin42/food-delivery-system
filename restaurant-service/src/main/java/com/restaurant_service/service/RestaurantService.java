package com.restaurant_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant_service.model.Restaurant;
import com.restaurant_service.repository.RestaurantRepository;

//service/RestaurantService.java
@Service
public class RestaurantService {
 @Autowired
 private RestaurantRepository restaurantRepository;

 public Restaurant create(Restaurant r) {
     return restaurantRepository.save(r);
 }

 public Restaurant get(Long id) {
     return restaurantRepository.findById(id).orElseThrow();
 }

 public List<Restaurant> getAll() {
     return restaurantRepository.findAll();
 }

 public void delete(Long id) {
	 restaurantRepository.deleteById(id);
 }

 // New method for complex queries with filters
 public List<Restaurant> getRestaurantsByFilters(String location, String cuisine, Double rating) {
     return restaurantRepository.findByFilters(location, cuisine, rating);
 }
}