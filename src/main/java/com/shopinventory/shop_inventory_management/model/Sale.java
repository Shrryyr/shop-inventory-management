package com.shopinventory.shop_inventory_management.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "sales")
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private Integer quantitySold;

    private LocalDateTime saleDate =  LocalDateTime.now();

    public Sale(Product product, Integer quantitySold) {
        this.product = product;
        this.quantitySold = quantitySold;
    }
}
