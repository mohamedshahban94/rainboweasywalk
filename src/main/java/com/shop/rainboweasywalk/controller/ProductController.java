package com.shop.rainboweasywalk.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.shop.rainboweasywalk.model.Product;
import com.shop.rainboweasywalk.service.ProductService;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService ps) {
        this.productService = ps;
    }

    @GetMapping
    public List<Product> list() {
        return productService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Integer id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Product create(@RequestBody Product p) {
        return productService.create(p);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Integer id, @RequestBody Product updatedProduct) {
        return productService.update(id, updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        productService.delete(id);
    }

    @PostMapping("/{id}/restock")
    public Product restock(@PathVariable Integer id,
                           @RequestParam("qty") int qty) {
        return productService.restock(id, qty);
    }
}
