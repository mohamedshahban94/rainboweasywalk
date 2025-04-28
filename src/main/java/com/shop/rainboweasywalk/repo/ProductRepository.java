package com.shop.rainboweasywalk.repo;

import com.shop.rainboweasywalk.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByCode(String code);

    // Search by name containing keyword
    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findAll(Specification<Product> spec);

    // Search by exact product code
//    Optional<Product> findByCode(String code);
}
