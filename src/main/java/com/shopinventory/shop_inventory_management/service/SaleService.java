package com.shopinventory.shop_inventory_management.service;


import com.shopinventory.shop_inventory_management.model.Product;
import com.shopinventory.shop_inventory_management.model.Sale;
import com.shopinventory.shop_inventory_management.repository.ProductRepository;
import com.shopinventory.shop_inventory_management.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    public SaleService(SaleRepository saleRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    public Sale recordSale(Long productId, Integer quantitySold){
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found with id:" + productId));

        if (product.getQuantity() < quantitySold) {
            throw new RuntimeException("Not enough stock available. Current stock: " + product.getQuantity());
        }

        product.setQuantity(product.getQuantity() - quantitySold);
        productRepository.save(product);

        Sale sale = new Sale(product, quantitySold);
        return saleRepository.save(sale);
    }

    public List<Sale> getAllSale(){
        return saleRepository.findAll();
    }
}
