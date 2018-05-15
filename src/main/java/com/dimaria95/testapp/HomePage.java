package com.dimaria95.testapp;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

	@FindBy(xpath = "//*[@id=\"the-pi-hut\"]/header/div/div/div[2]/div/ul/li[4]/a")
	//@FindBy(how=How.CSS, using="a.cart-toggle")
	WebElement cartButton;

	@FindBy(xpath = "//*[@id=\"the-pi-hut\"]/header/div/div/div[2]/div/ul/li[3]")
	WebElement loginButton;

	@FindBy(id = "customer_logout_link")
	WebElement logoutButton;

	@FindBy(how = How.CLASS_NAME, using = "grid-link__title")
	List<WebElement> products;

	@FindBy(xpath = "//*[@id=\"the-pi-hut\"]/header/div/div/div[2]/div/ul/li[2]/form/input[1]")
	WebElement hiddenSearch;

	@FindBy(xpath = "//*[@id=\"the-pi-hut\"]/header/div/div/div[2]/div/ul/li[2]/form/input[2]")
	WebElement searchInput;

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public LoginPage goToLoginPage() {
		loginButton.click();
		return new LoginPage(driver);
	}

	public CartPage goToCart() {
		cartButton.click();
		return new CartPage(driver);
	}

	public void logOut() {
		logoutButton.click();
	}

	public SearchResPage search(String query) {

		if (hiddenSearch.isDisplayed()) {
			hiddenSearch.click();
		}
		// searchInput.clear();
		searchInput.sendKeys(query);
		searchInput.sendKeys(Keys.ENTER);
		return new SearchResPage(driver);
	}

	public boolean isUrlCorrect() {
		String url = driver.getCurrentUrl();
		return url.equals("https://thepihut.com/");
	}

	public String getProductName(int index) {
		return products.get(index).getText();
	}

	public ProductPage clickOnProduct(int index) {
		products.get(index).click();
		return new ProductPage(driver);
	}

}