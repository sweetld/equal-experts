package com.tempestiva;

import com.tempestiva.step3.Product;
import com.tempestiva.step3.ShoppingCart;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/*

Given:

An empty shopping cart
And a product, Dove Soap with a unit price of 39.99
And another product, Axe Deo with a unit price of 99.99
And a sales tax rate of 12.5%
When:

The user adds 2 Dove Soaps to the shopping cart
And then adds 2 Axe Deos to the shopping cart
Then:

The shopping cart should contain 2 Dove Soaps each with a unit price of 39.99
And the shopping cart should contain 2 Axe Deos each with a unit price of 99.99
And the total sales tax amount for the shopping cart should equal 35.00
And the shopping cart’s total price should equal 314.96

*/
public class StepThreeTest {
    private ShoppingCart shoppingCart;
    private Product doveSoap;
    private Product axeDeo;

    @Before
    public final void setup() {
        // Create an empty shopping cart
        shoppingCart = new ShoppingCart();

        // Create a Product, Dove Soap, with unit price of 39.99
        doveSoap = new Product("Dove Soap", new BigDecimal("39.99"));

        // Create a Product, Axe Deo, with unit price of 99.99
        axeDeo = new Product("Axe Deo", new BigDecimal("99.99"));
    }

    @After
    public final void tearDown() {
        // Cleanup
        shoppingCart = null;
        doveSoap = null;
        axeDeo = null;
    }

    @Test
    public final void whenAnotherThreeDoveSoapsAddedThenTotalPriceShouldBeEightTimesUnitPrice() {
        // When
        shoppingCart.add(doveSoap);
        shoppingCart.add(doveSoap);
        shoppingCart.add(axeDeo);
        shoppingCart.add(axeDeo);

        // Then
        Assert.assertEquals(new BigDecimal("314.96"), shoppingCart.getTotal());
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
