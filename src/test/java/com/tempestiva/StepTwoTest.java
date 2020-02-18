package com.tempestiva;

import lombok.extern.slf4j.Slf4j;
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
And then adds another 3 Dove Soaps to the shopping cart
Then:

The shopping cart should contain 8 Dove Soaps each with a unit price of 39.99
And the shopping cart’s total price should equal 319.92

*/
@Slf4j
public class StepTwoTest {
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
    public final void addAdditionalProductsOfTheSameTypeToTheShoppingCart() {
        // When:
        // The user adds 5 Dove Soaps to the shopping cart
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            products.add(doveSoap);
        }
        shoppingCart.add(products);
        // And then adds another 3 Dove Soaps to the shopping cart
        shoppingCart.add(doveSoap);
        shoppingCart.add(doveSoap);
        shoppingCart.add(doveSoap);

        // Then:
        // The shopping cart should contain 8 Dove Soaps each with a unit price of 39.99
        final int[] count = {0};
        shoppingCart.getContents().forEach(product -> {
            Assert.assertEquals("Dove Soap", product.getName());
            Assert.assertEquals(new BigDecimal("39.99"), product.getPrice());
            count[0]++;
        });
        Assert.assertEquals(8, count[0]);
        // And the shopping cart’s total price should equal 319.92
        Assert.assertEquals(new BigDecimal("319.92"), shoppingCart.getTotal());
    }

    @Test
    public final void whenAnotherThreeDoveSoapsAddedThenTotalPriceShouldBeEightTimesUnitPrice() {
        // When
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            products.add(doveSoap);
        }
        shoppingCart.add(products);
        shoppingCart.add(doveSoap);
        shoppingCart.add(doveSoap);
        shoppingCart.add(doveSoap);

        // Then
        Assert.assertEquals(new BigDecimal("319.92"), shoppingCart.getTotal());
    }

    @Test
    public final void whenEightProductsAddedThenCartShouldContainEightThings() {
        // When
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            products.add(doveSoap);
        }
        shoppingCart.add(products);

        // Then
        Assert.assertEquals(8, shoppingCart.getContents().size());
    }

    @Test
    public final void whenNoProductsAddedThenCartShouldBeEmpty() {
        Assert.assertEquals(0, shoppingCart.getContents().size());
    }
}
