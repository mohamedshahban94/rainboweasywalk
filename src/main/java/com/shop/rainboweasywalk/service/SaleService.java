package com.shop.rainboweasywalk.service;

import com.shop.rainboweasywalk.model.Product;
import com.shop.rainboweasywalk.model.Sale;
import com.shop.rainboweasywalk.repo.SaleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepo;
    private final ProductService productService;

    public SaleService(SaleRepository saleRepo, ProductService productService) {
        this.saleRepo = saleRepo;
        this.productService = productService;
    }

    /**
     * Create a sale:
     *  - check stock
     *  - decrement stock
     *  - compute totalPrice (qty * product.sellingPrice)
     *  - apply discount
     *  - persist both Sale and updated Product
     */

    @Transactional
    public Sale recordSale(Integer productId, int qty, int discount) {
        Product prod = productService.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        if (prod.getStock() < qty) {
            throw new IllegalArgumentException("Insufficient stock");
        }

        // decrement stock
        prod.setStock(prod.getStock() - qty);
        productService.update(prod);

        // compute
        double gross = prod.getSellprice() * qty;
        if(discount>gross){
            throw new IllegalArgumentException("Discount exceeds total price");
        }
        double finalAmt = gross - discount;

        Sale sale = new Sale();
        sale.setProduct(prod);
        sale.setQuantity(qty);
        sale.setDiscount(discount);
        sale.setTotalPrice((int) Math.round(finalAmt));
        // soldAt is defaulted in entity

        return saleRepo.save(sale);
    }

    public List<Sale> allSales() {
        return saleRepo.findAll();
    }

    public void delete(Integer id) {
        saleRepo.deleteById(id);
    }


}
