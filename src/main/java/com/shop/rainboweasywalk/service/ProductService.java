package com.shop.rainboweasywalk.service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.shop.rainboweasywalk.model.Product;
import com.shop.rainboweasywalk.repo.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> listAll() {
        return productRepo.findAll();
    }

    public Product create(Product p) {
        return productRepo.save(p);
    }

    public Optional<Product> findById(Integer id) {
        return productRepo.findById(id);
    }

    public Product update(Integer id, Product updatedProduct) {
        Product existing = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        existing.setCode(updatedProduct.getCode());
        existing.setName(updatedProduct.getName());
        existing.setBrand(updatedProduct.getBrand());
        existing.setSize(updatedProduct.getSize());
        existing.setColor(updatedProduct.getColor());
        existing.setStock(updatedProduct.getStock());
        existing.setCostPrice(updatedProduct.getCostPrice());
        existing.setSellprice(updatedProduct.getSellprice());
        existing.setAddedOn(updatedProduct.getAddedOn());
        return productRepo.save(existing);
    }

    public Product update(Product product) {
        if (!productRepo.existsById(product.getId())) {
            throw new RuntimeException("Product not found");
        }
        return productRepo.save(product);
    }


    public void delete(Integer id) {
        productRepo.deleteById(id);
    }

    /** Restock product by id **/
    @Transactional
    public Product restock(Integer id, int qtyToAdd) {
        Product p = productRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No product"));
        p.setStock(p.getStock() + qtyToAdd);
        return productRepo.save(p);
    }
}
