package com.shopinventory.shop_inventory_management.repository;

import com.shopinventory.shop_inventory_management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
