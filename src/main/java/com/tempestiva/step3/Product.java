package com.tempestiva.step3;

import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
    Experience tells me that you should also include a unique Product Code of some sort as well
    Which is then used to implement custom hashCode and equals methods for Product
    However, keeping simplicity in mind, at no point has it become necessary yet...
*/

@Value
public class Product {
    private String name;
    private BigDecimal price;
    // I took the approach that the sales tax rate could be different for different products
    // Therefore it should be an attribute of the product itself
    private BigDecimal salesTaxRate;
    private BigDecimal salesTax;

    public Product(String name, BigDecimal price, BigDecimal salesTaxRate) throws IllegalArgumentException {
        if (name == null || name.length() < 1)
            throw new IllegalArgumentException("Product has no Name");
        if (price == null)
            throw new IllegalArgumentException("Product has no Price");
        if (salesTaxRate == null)
            throw new IllegalArgumentException("Product has no Sales Tax Rate");
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Product Price is negative");
        }
        if (salesTaxRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Product Sales Tax Rate is negative");
        }
        this.name = name;
        this.price = price.setScale(2, RoundingMode.HALF_UP);
        this.salesTaxRate = salesTaxRate.setScale(2, RoundingMode.HALF_UP);
        // If we calculate the sales tax on this product now, then we only have to calculate it once
        this.salesTax = price.multiply(salesTaxRate).divide(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP);
    }

}
