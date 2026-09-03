package com.shopinventory.shop_inventory_management.service;


import com.shopinventory.shop_inventory_management.model.Supplier;
import com.shopinventory.shop_inventory_management.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id ) {
        return supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
    }

    public Supplier updateSupplier(Long id, Supplier updatedSupplier) {
        Supplier existingSupplier = getSupplierById(id);

        existingSupplier.setName(updatedSupplier.getName());
        existingSupplier.setContactNumber(updatedSupplier.getContactNumber());

        return supplierRepository.save(existingSupplier);
    }

    public void deleteSupplier(Long id) {
        Supplier supplier = getSupplierById(id);
        supplierRepository.delete(supplier);
    }
}
