package com.tempestiva.step1;

import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class Product {
    private String name;
    private BigDecimal price;

}
