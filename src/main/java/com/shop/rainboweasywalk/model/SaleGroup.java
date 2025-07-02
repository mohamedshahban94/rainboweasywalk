package com.shop.rainboweasywalk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sale_groups")
public class SaleGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime soldAt = LocalDateTime.now();

    private Integer totalAmount;
    private Integer totalDiscount;


    @OneToMany(mappedBy = "saleGroup", cascade = CascadeType.ALL)
    @JsonIgnore // optional unless you're using this as a response
    private List<Sale> items = new ArrayList<>();
}

