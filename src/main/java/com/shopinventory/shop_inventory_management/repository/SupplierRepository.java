package com.shopinventory.shop_inventory_management.repository;

import com.shopinventory.shop_inventory_management.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
