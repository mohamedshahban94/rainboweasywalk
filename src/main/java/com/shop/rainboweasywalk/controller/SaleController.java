package com.shop.rainboweasywalk.controller;

import com.shop.rainboweasywalk.model.Sale;
import com.shop.rainboweasywalk.service.SaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService ss) {
        this.saleService = ss;
    }

    @GetMapping
    public List<Sale> all() {
        return saleService.allSales();
    }

    @PostMapping
    public Sale record(@RequestParam("productId") Integer pid,
                       @RequestParam("qty") int qty,
                       @RequestParam("discount") int discount) {
        return saleService.recordSale(pid, qty, discount);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        saleService.delete(id);
    }

}
