package com.shop.rainboweasywalk.controller;

import com.shop.rainboweasywalk.model.Product;
import com.shop.rainboweasywalk.model.Sale;
import com.shop.rainboweasywalk.model.SaleGroup;
import com.shop.rainboweasywalk.repo.SaleGroupRepository;
import com.shop.rainboweasywalk.repo.SaleRepository;
import com.shop.rainboweasywalk.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/billing")
@RequiredArgsConstructor
public class BillingController {

    private final ProductService productService;
    private final SaleRepository saleRepo;
    private final SaleGroupRepository saleGroupRepo;

    // temporary billing cart
    private final Map<Integer, Sale> cart = new HashMap<>();

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestParam Integer productId,
                                       @RequestParam Integer qty,
                                       @RequestParam Integer discount) {
        Product p = productService.findById(productId).orElseThrow();
        if (p.getStock() < qty) return ResponseEntity.badRequest().body("Not enough stock");

        double gross = p.getSellprice() * qty;
        if (discount > gross) return ResponseEntity.badRequest().body("Invalid discount");

        Sale sale = new Sale();
        sale.setProduct(p);
        sale.setQuantity(qty);
        sale.setDiscount(discount);
        sale.setTotalPrice((int) (gross - discount));
        cart.put(productId, sale);

        return ResponseEntity.ok("Added to cart");
    }

    @PostMapping("/checkout")
    @Transactional
    public ResponseEntity<?> checkout() {
        if (cart.isEmpty()) return ResponseEntity.badRequest().body("Cart is empty");

        int totalAmount = 0, totalDiscount = 0;
        SaleGroup group = new SaleGroup();

        for (Sale sale : cart.values()) {
            Product p = sale.getProduct();
            p.setStock(p.getStock() - sale.getQuantity());
            productService.update(p);

            sale.setSaleGroup(group);
            totalAmount += sale.getTotalPrice();
            totalDiscount += sale.getDiscount();
        }

        group.setTotalAmount(totalAmount);
        group.setTotalDiscount(totalDiscount);
        group.setItems(new ArrayList<>(cart.values()));

        saleGroupRepo.save(group);
        cart.clear();

        return ResponseEntity.ok("Billing completed");
    }

    @GetMapping("/cart")
    public Collection<Sale> viewCart() {
        return cart.values();
    }

    @DeleteMapping("/cart/{productId}")
    public ResponseEntity<?> removeFromCart(@PathVariable Integer productId) {
        cart.remove(productId);
        return ResponseEntity.ok("Removed");
    }
}


