package com.restaurant_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.restaurant_service.model.Restaurant;

//repository/RestaurantRepository.java
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
 // Basic queries
 List<Restaurant> findByCuisineType(String cuisineType);
 
 // Complex query with location, cuisine, and rating
 @Query("SELECT r FROM Restaurant r WHERE " +
         "(r.location LIKE %:location% OR :location IS NULL) AND " +
         "(r.cuisineType LIKE %:cuisine% OR :cuisine IS NULL) AND " +
         "(r.rating >= :rating OR :rating IS NULL) AND " +
         "r.active = true")
 List<Restaurant> findByFilters(@Param("location") String location, 
                                @Param("cuisine") String cuisine,
                                @Param("rating") Double rating);
}