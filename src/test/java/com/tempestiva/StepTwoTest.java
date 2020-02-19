package com.tempestiva;

import com.tempestiva.step2.Product;
import com.tempestiva.step2.ShoppingCart;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

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
        for (int i = 0; i < 5; i++) {
            shoppingCart.add(doveSoap);
        }
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
        for (int i = 0; i < 5; i++) {
            shoppingCart.add(doveSoap);
        }
        shoppingCart.add(doveSoap);
        shoppingCart.add(doveSoap);
        shoppingCart.add(doveSoap);

        // Then
        Assert.assertEquals(new BigDecimal("319.92"), shoppingCart.getTotal());
    }

    @Test
    public final void whenEightProductsAddedThenCartShouldContainEightThings() {
        // When
        for (int i = 0; i < 8; i++) {
            shoppingCart.add(doveSoap);
        }

        // Then
        Assert.assertEquals(8, shoppingCart.getContents().size());
    }

    @Test
    public final void whenNoProductsAddedThenCartShouldBeEmpty() {
        Assert.assertEquals(0, shoppingCart.getContents().size());
    }
}
