package com.shop.rainboweasywalk.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sale_group_id")
    private SaleGroup saleGroup;

    private Integer quantity;
    private Integer discount;
    private Integer totalPrice;

    private LocalDateTime soldAt = LocalDateTime.now();

    // Getters and Setters
}
