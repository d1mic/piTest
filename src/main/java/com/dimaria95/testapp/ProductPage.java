package com.dimaria95.testapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

	@FindBy(id = "AddToCartText")
	WebElement addToCartButton;

	// @FindBy(how = How.CLASS_NAME, using="icon-cart")
	@FindBy(xpath = "//*[@id=@*]/header/div/div/div[2]/div/ul/li[4]")
	WebElement cartButton;

	@FindBy(xpath = "//*[@id=@*]/header/div/div/div[2]/div/ul/li[4]/a/span[2]")
	WebElement cartCounter;

	@FindBy(how = How.CLASS_NAME, using = "money")
	WebElement price;

	public ProductPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String getAddToCart() {
		return addToCartButton.getText().toLowerCase().trim();
	}

	public double addToCart() {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AddToCartText")));
		addToCartButton.click();

		String m_price = price.getText();
		m_price = m_price.substring(1, m_price.indexOf(" "));
		return Double.parseDouble(m_price);
	}

	public CartPage goToCart() {
		cartButton.click();
		return new CartPage(driver);
	}

}
