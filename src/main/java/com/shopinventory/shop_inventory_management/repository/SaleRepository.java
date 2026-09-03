package com.shopinventory.shop_inventory_management.repository;

import com.shopinventory.shop_inventory_management.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
