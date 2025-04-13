package com.order_service.repository;

import com.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Optional: custom methods if needed
    List<Order> findByRestaurantId(Long restaurantId);
    List<Order> findByStatus(String status);
}
