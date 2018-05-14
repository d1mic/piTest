package com.dimaria95.testapp;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

	public HomePage homePage;
	public SearchResPage searchPage;

	@Test
	public void invalidSearch() {

		System.out.println("START invalidSearch TEST");

		// Go to home page
		homePage = new HomePage(driver);

		// Search for some invalid item
		String query = "de nada";
		searchPage = homePage.search(query);

		// check if search page is opened and if number of results is zero
		Assert.assertEquals(query, searchPage.getSearchTitle());
		Assert.assertEquals(query, searchPage.getResultText());
		Assert.assertEquals(searchPage.getNumOfResults(), 0);
	}

	@Test
	public void priceSearch() {

		System.out.println("START priceSearch TEST");

		// Go to home page
		homePage = new HomePage(driver);

		// Search for some valid item
		String query = "pi";
		searchPage = homePage.search(query);

		// check if search page is opened and text is correctly displayed
		Assert.assertEquals(query, searchPage.getSearchTitle());
		Assert.assertEquals(query, searchPage.getResultText());

		// get number of results and compare if the number is correct
		int numOfResults = searchPage.getNumOfResults();
		Assert.assertEquals(numOfResults, 744);

		// select first price range
		String cheapPrice = searchPage.selectCheapPrice();

		// check if price range is correct
		double priceMin = searchPage.getPriceMinimum(cheapPrice);
		double priceMax = searchPage.getPriceMaximum(cheapPrice);
		Assert.assertEquals(priceMin, 0.0);
		Assert.assertEquals(priceMax, 20.0);

		// check if prices of items displayed are in selected range
		Assert.assertTrue(searchPage.arePricesInRange(priceMin, priceMax));

		// check if number of results has changed
		double differentResult = searchPage.getNumOfResults();
		Assert.assertNotEquals(differentResult, numOfResults);

	}

	@Test
	public void categorySearch() {

		System.out.println("START categorySearch TEST");

		homePage = new HomePage(driver);

		// Search for some item
		String query = "pie";
		searchPage = homePage.search(query);

		// Check if search page is displayed correctly
		Assert.assertEquals(query, searchPage.getSearchTitle());
		Assert.assertEquals(query, searchPage.getResultText());

		// Select category from sidebar navigation
		int categoryNumber = 0;
		String caregoryFromNav = searchPage.getCategoryNav(categoryNumber);
		searchPage.selectCategory(categoryNumber);

		// Compare sidebar text with the category from search title
		String caregoryTitle = searchPage.getCategoryTitle();

		Assert.assertEquals(caregoryTitle, caregoryFromNav);

	}

	@AfterMethod(alwaysRun = true)
	public void goHome() {

		// return to home page and check if it is correct
		homePage = searchPage.goHome();
		Assert.assertTrue(homePage.isUrlCorrect());
	}

}
