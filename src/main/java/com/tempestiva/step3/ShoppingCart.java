package com.tempestiva.step3;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {
    private List<Product> contents = new ArrayList<>();
    private BigDecimal total = BigDecimal.ZERO;
    private BigDecimal tax = BigDecimal.ZERO;

    public void add(Product product) throws IllegalArgumentException {
        if (product == null)
            throw new IllegalArgumentException("Cannot add null to the Shopping Cart");
        contents.add(product);
        // Keep a running total of the tax
        tax = tax.add(product.getSalesTax());
        // Keep an overall running total
        total = total.add(product.getPrice().add(product.getSalesTax()));
    }
}
