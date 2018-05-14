package com.dimaria95.testapp;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

	public HomePage homePage;
	public LoginPage loginPage;
	public MyAccountPage accPage;
	public ProductPage prodPage;
	public boolean loggedIn = false;

	@BeforeMethod()
	public void setupLogin() {
		// Go to home page
		homePage = new HomePage(driver);

		// if logged in -> log out
		if (loggedIn) {
			homePage.logOut();
			loggedIn = false;
		}
	}

	// TODO: do reading from props 

	@Test
	public void testPositiveLogin() {

		System.out.println("START testPositiveLogin TEST");

		// Click on login button and check if login page is opened 
		loginPage = homePage.goToLoginPage();
		Assert.assertEquals(loginPage.getLoginTitle(), "login");

		// Sign into account and check if name, logout button and order history title are shown correctly
		accPage = loginPage.signIn("dimic.nikola@gmail.com", "toptal123");
		Assert.assertEquals(accPage.getName(), "nikola dimic");
		Assert.assertTrue(accPage.checkOrderHistory());
		Assert.assertEquals(accPage.getLogOutButton(), "log out");
		
		// set indicator for login to true
		loggedIn = true;
		
		// Click on logout button and check if user is returned to home page 
		homePage = accPage.logOut();
		Assert.assertTrue(homePage.isUrlCorrect());

	}

	@Test
	public void testNegativeLogin() {
		
		System.out.println("START testNegativeLogin TEST");
		
		// Click on login button and check if login page is opened 
		loginPage = homePage.goToLoginPage();
		Assert.assertEquals(loginPage.getLoginTitle(), "login");
		
		// Sign in with invalid credentials
		loginPage.signIn("dimic.nikola@gil.com", "toptal123");
		Assert.assertTrue(loginPage.isErrorMsgDisplayed());

		// Go back to home page and check is url correct
		homePage = loginPage.goHome();
		Assert.assertTrue(homePage.isUrlCorrect());

	}
	
	

}
