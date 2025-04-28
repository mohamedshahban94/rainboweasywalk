package com.shop.rainboweasywalk.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.shop.rainboweasywalk.model.Product;
import com.shop.rainboweasywalk.service.ProductService;
import java.util.List;
import java.util.Optional;

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
    // 1. Filter products (all parameters optional)
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(
            @RequestParam Optional<String> brand,
            @RequestParam Optional<String> color,
            @RequestParam Optional<Integer> size,
            @RequestParam Optional<String> gender,
            @RequestParam Optional<String> category) {

        List<Product> products = productService.filterProducts(brand, color, size, gender, category);
        return ResponseEntity.ok(products);
    }
    // 2. Search products by name
    @GetMapping("/search/name")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String keyword) {
        List<Product> products = productService.searchProductsByName(keyword);
        return ResponseEntity.ok(products);
    }

    // 3. Search product by code
    @GetMapping("/search/code")
    public ResponseEntity<Product> searchProductByCode(@RequestParam String code) {
        Product product = productService.searchProductByCode(code);
        return ResponseEntity.ok(product);
    }
}
