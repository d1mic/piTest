package com.dimaria95.testapp;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

	public HomePage homePage;
	public ProductPage prodPage;
	public CartPage cartPage;

	@BeforeMethod(alwaysRun= true)
	public void initEmptyCart() {

		// go to home page
		homePage = new HomePage(driver);

		// go to cart and check if cart title is shown
		cartPage = homePage.goToCart();
		Assert.assertTrue(cartPage.checkCartTitle());

		// if cart is not empty -> remove all items from cart and check if cart is empty
		if (cartPage.getCounter() != 0) {
			cartPage.removeAllItems();
		}
		Assert.assertEquals(cartPage.getCounter(), 0);

		// go back to home page
		homePage = cartPage.goHome();
	}

	@Test(groups = { "cart"  , "smoke"})
	public void AddOneItemToCart() {

		System.out.println("START AddOneItemToCart TEST");

		// Select item no 3 and go on product page , check if product page is displayed
		prodPage = homePage.clickOnProduct(2);
		Assert.assertEquals(prodPage.getAddToCart(), "add to cart");

		// Get price from product page and add product to the cart
		double itemPrice = prodPage.addToCart();

		// Go to cart and check cart title
		cartPage = prodPage.goToCart();
		Assert.assertTrue(cartPage.checkCartTitle());

		// Get price from cart and compare
		double cartPrice = cartPage.getTotalPrice();
		Assert.assertEquals(itemPrice, cartPrice);

		// Get number of items in cart from navigation bar
		int numOfItems = cartPage.getCounter();
		Assert.assertEquals(numOfItems, 1);

		// Go back to main page
		homePage = cartPage.goHome();
		Assert.assertTrue(homePage.isUrlCorrect());

	}

	@Test(groups = { "cart" })
	public void AddMultipleSameItemsToCart() {

		System.out.println("START AddMultipleSameItemsToCart TEST");

		// Select item no 3 and go on product page
		prodPage = homePage.clickOnProduct(1);
		Assert.assertEquals(prodPage.getAddToCart(), "add to cart");

		// Get price from product page and add product two times to the cart
		double itemPrice = 0;
		for (int i = 0; i < 2; i++) {
			itemPrice += prodPage.addToCart();
		}
		// Go to cart
		cartPage = prodPage.goToCart();
		Assert.assertTrue(cartPage.checkCartTitle());

		// Get price from cart and compare
		double cartPrice = cartPage.getTotalPrice();
		Assert.assertEquals(itemPrice, cartPrice);

		// Get number of items in cart from navigation bar and compare
		int numOfItems = cartPage.getCounter();
		Assert.assertEquals(numOfItems, 2);

		// Go back to main page
		homePage = cartPage.goHome();
		Assert.assertTrue(homePage.isUrlCorrect());

	}

	@Test(groups = { "cart"  })
	public void AddMultipleDifferentItems() {

		System.out.println("START AddMultipleDifferentItems TEST");

		// Select item no 2 and go on product page
		prodPage = homePage.clickOnProduct(1);
		Assert.assertEquals(prodPage.getAddToCart(), "add to cart");

		// Get price from product page and add product to the cart
		double itemPrice = 0;
		itemPrice += prodPage.addToCart();

		// Go back to home page
		homePage = prodPage.goHome();
		Assert.assertTrue(homePage.isUrlCorrect());

		// Select item no 3 and go on product page
		prodPage = homePage.clickOnProduct(2);
		Assert.assertEquals(prodPage.getAddToCart(), "add to cart");

		// Get price from product page and add product to the cart
		itemPrice += prodPage.addToCart();

		// Go to cart
		cartPage = prodPage.goToCart();
		Assert.assertTrue(cartPage.checkCartTitle());

		// Get price from cart and compare
		double cartPrice = cartPage.getTotalPrice();
		Assert.assertEquals(itemPrice, cartPrice);

		// Get num of items in cart from navbar and compare
		int numOfItems = cartPage.getCounter();
		Assert.assertEquals(numOfItems, 2);

		// Go back to main page
		homePage = cartPage.goHome();
		Assert.assertTrue(homePage.isUrlCorrect());

	}

	@Test(groups = { "cart" ,"smoke" })
	public void removeOneItem() {

		System.out.println("START removeOneItem TEST");

		// Select item no 3 and go on product page
		prodPage = homePage.clickOnProduct(2);
		Assert.assertEquals(prodPage.getAddToCart(), "add to cart");

		// Add product to the cart
		prodPage.addToCart();

		// Go to cart
		cartPage = prodPage.goToCart();
		Assert.assertTrue(cartPage.checkCartTitle());

		// Get num of items in cart from navbar
		int numOfItems = cartPage.getCounter();
		Assert.assertEquals(numOfItems, 1);

		// remove item from cart
		cartPage.removeAllItems();

		// check if items are removed and empty cart msg is displayed
		numOfItems = cartPage.getCounter();
		Assert.assertEquals(numOfItems, 0);
		Assert.assertTrue(cartPage.isEmptyMsg());

		// Go back to main page
		homePage = cartPage.goHome();
		Assert.assertTrue(homePage.isUrlCorrect());

	}

}
