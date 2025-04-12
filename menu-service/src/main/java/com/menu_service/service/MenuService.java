package com.menu_service.service;

import com.menu_service.exception.ResourceNotFoundException;
import com.menu_service.feign.RestaurantClient;
import com.menu_service.model.Menu;
import com.menu_service.repository.MenuRepository;
import com.menu_service.dto.RestaurantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantClient restaurantClient;

    public Menu create(Menu menu) {
        // Validate if restaurant exists before saving menu
        try {
            RestaurantDto restaurant = restaurantClient.getRestaurantById(menu.getRestaurantId());
            if (restaurant == null) {
                throw new ResourceNotFoundException("Restaurant not found with ID: " + menu.getRestaurantId());
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException("Restaurant service not reachable or invalid ID");
        }

        return menuRepository.save(menu);
    }

    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    public Menu getById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu not found with ID: " + id));
    }

    public void delete(Long id) {
        if (!menuRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Menu not found with ID: " + id);
        }
        menuRepository.deleteById(id);
    }

    public List<Menu> getByRestaurantId(Long restaurantId) {
        return menuRepository.findByRestaurantId(restaurantId);
    }
}
