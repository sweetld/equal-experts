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
        doveSoap = new Product("Dove Soap", new BigDecimal("39.99"), new BigDecimal("12.5"));

        // Create a Product, Axe Deo, with unit price of 99.99
        axeDeo = new Product("Axe Deo", new BigDecimal("99.99"), new BigDecimal("12.5"));
    }

    @After
    public final void tearDown() {
        // Cleanup
        shoppingCart = null;
        doveSoap = null;
        axeDeo = null;
    }

    @Test
    public final void calculateTheTaxRateOfTheShoppingCartWithMultipleItems() {
        // When:
        // The user adds 2 Dove Soaps to the shopping cart
        shoppingCart.add(doveSoap);
        shoppingCart.add(doveSoap);
        // And then adds 2 Axe Deos to the shopping cart
        shoppingCart.add(axeDeo);
        shoppingCart.add(axeDeo);

        // Then:
        // The shopping cart should contain 2 Dove Soaps each with a unit price of 39.99
        // And the shopping cart should contain 2 Axe Deos each with a unit price of 99.99
        final int[] count = {0,0};
        shoppingCart.getContents().forEach(product -> {
            if (product.getName().equals("Dove Soap")) {
                Assert.assertEquals(new BigDecimal("39.99"), product.getPrice());
                count[0]++;
            }
            if (product.getName().equals("Axe Deo")) {
                Assert.assertEquals(new BigDecimal("99.99"), product.getPrice());
                count[1]++;
            }
        });
        Assert.assertEquals(2, count[0]);
        Assert.assertEquals(2, count[1]);
        // And the total sales tax amount for the shopping cart should equal 35.00
        Assert.assertEquals(new BigDecimal("35.00"), shoppingCart.getTax());
        // And the shopping cart’s total price should equal 314.96
        Assert.assertEquals(new BigDecimal("314.96"), shoppingCart.getTotal());
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

    @Test(expected = IllegalArgumentException.class)
    public final void whenNullProductAddedThenShouldThrowException() {
        shoppingCart.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void whenProductWithoutPriceAddedThenShouldThrowException() {
        shoppingCart.add(new Product("Dove Soap", null, new BigDecimal("12.5")));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void whenProductWithoutNameAddedThenShouldThrowException() {
        shoppingCart.add(new Product(null, new BigDecimal("39.99"), new BigDecimal("12.5")));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void whenProductWithoutNameAndPriceAddedThenShouldThrowException() {
        shoppingCart.add(new Product(null, null, new BigDecimal("12.5")));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void whenProductWithEmptyNameAddedThenShouldThrowException() {
        shoppingCart.add(new Product("", new BigDecimal("39.99"), new BigDecimal("12.5")));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void whenProductWithNegativePriceAddedThenShouldThrowException() {
        shoppingCart.add(new Product("Dove Soap", new BigDecimal("-39.99"), new BigDecimal("12.5")));
    }
}
