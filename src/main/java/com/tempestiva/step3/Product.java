package com.tempestiva.step3;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Product {
    private String name;
    private BigDecimal price;

}
