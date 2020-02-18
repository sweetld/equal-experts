package com.tempestiva;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*

Given:

An empty shopping cart
And a product, Dove Soap with a unit price of 39.99
When:

The user adds 5 Dove Soaps to the shopping cart
Then:

The shopping cart should contain 5 Dove Soaps each with a unit price of 39.99
And the shopping cart’s total price should equal 199.95

*/
public class StepOneTest {
    private ShoppingCart shoppingCart;
    private Product doveSoap;

    @Before
    public final void setup() {
        // Create an empty shopping cart
        shoppingCart = new ShoppingCart();

        // Create a Product, Dove Soap, with unit price of 39.99
        doveSoap = new Product("Dove Soap", new BigDecimal("39.99"));
    }

    @After
    public final void tearDown() {
        // Cleanup
        shoppingCart = null;
        doveSoap = null;
    }

    @Test
    public final void whenFiveDoveSoapsAddedThenTotalPriceShouldBeFiveTimesUnitPrice() {
        // When
        List<Product> items = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            items.add(doveSoap);
        }
        shoppingCart.add(items);

        // Then
        Assert.assertEquals(new BigDecimal("199.95"), shoppingCart.getTotal());
    }
}
