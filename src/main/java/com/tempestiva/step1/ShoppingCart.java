package com.tempestiva.step1;

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
            throw new IllegalArgumentException("Cannot add null to the Shopping Cart");
        if (product.getPrice() == null)
            throw new IllegalArgumentException("Product has no Price");
        if (product.getPrice().compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Product Price is negative");
        if (product.getName() == null || product.getName().length() < 1)
            throw new IllegalArgumentException("Product has no Name");
        contents.add(product);
        total = total.add(product.getPrice());
    }
}
