package com.tempestiva.step2;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {
    private List<Product> contents = new ArrayList<>();
    private BigDecimal total = BigDecimal.ZERO;

    public void add(Product product) throws IllegalArgumentException {
        if (product == null)
            throw new IllegalArgumentException("Cannot add a null to the Shopping Cart");
        if (product.getPrice() == null)
            throw new IllegalArgumentException("Product has no Price");
        contents.add(product);
        total = total.add(product.getPrice());
    }
}
