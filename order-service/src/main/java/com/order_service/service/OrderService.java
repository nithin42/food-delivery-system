package com.order_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order_service.dto.MenuDto;
import com.order_service.exception.ResourceNotFoundException;
import com.order_service.feign.MenuClient;
import com.order_service.feign.RestaurantClient;
import com.order_service.model.Order;
import com.order_service.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuClient menuClient;

    @Autowired
    private RestaurantClient restaurantClient;

    public Order placeOrder(Order order) {
        // Validate restaurant
        restaurantClient.getRestaurantById(order.getRestaurantId());

        // Validate menu and calculate total
        MenuDto menu = menuClient.getMenuById(order.getMenuId());
        double total = menu.getPrice() * order.getQuantity();
        order.setTotalPrice(total);
        order.setStatus("PLACED");

        return orderRepository.save(order);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
