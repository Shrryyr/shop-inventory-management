package com.shopinventory.shop_inventory_management.controller;


import com.shopinventory.shop_inventory_management.model.Sale;
import com.shopinventory.shop_inventory_management.service.SaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public Sale recordSale(@RequestParam Long productId, @RequestParam Integer quantitySold) {
        return saleService.recordSale(productId, quantitySold);
    }

    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }
}
