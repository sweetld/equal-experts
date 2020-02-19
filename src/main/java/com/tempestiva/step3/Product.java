package com.tempestiva.step3;

import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Value
public class Product {
    private String name;
    private BigDecimal price;
    private BigDecimal salesTaxRate;
    private BigDecimal salesTax;

    public Product(String name, BigDecimal price, BigDecimal salesTaxRate) throws IllegalArgumentException {
        if (price == null)
            throw new IllegalArgumentException("Product has no Price");
        if (price.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Product Price is negative");
        if (name == null || name.length() < 1)
            throw new IllegalArgumentException("Product has no Name");
        this.name = name;
        this.price = price;
        // I took the approach that the sales tax rate could be different for different products
        // Therefore it should be an attribute of the product itself
        this.salesTaxRate = salesTaxRate;
        // If we calculate the sales tax on this product now, then we only have to calculate it once
        this.salesTax = price.multiply(salesTaxRate).divide(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP);
    }

}
