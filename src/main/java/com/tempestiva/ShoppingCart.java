package com.tempestiva;

import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {
    private List<Product> contents = new ArrayList<>();
    private BigDecimal total = BigDecimal.ZERO;

    public void add(final List<Product> products) {
        products.forEach(product -> {
            total = total.add(product.getPrice());
            contents.add(product);
        });
    }

    public void add(Product product) {
        contents.add(product);
        total = total.add(product.getPrice());
    }
}
