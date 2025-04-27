package com.shop.rainboweasywalk.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;
    private String name;
    private String brand;
    private String type;
    private String gender;
    private Integer size;
    private String color;
    private Integer sellprice;
    private Integer costPrice;
    private Integer stock;

    private LocalDateTime addedOn = LocalDateTime.now();

    // Getters and Setters (use Lombok if you prefer)
}
