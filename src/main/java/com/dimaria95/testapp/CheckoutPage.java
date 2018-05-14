package com.dimaria95.testapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {

	@FindBy(id = "main-header")
	WebElement contactInformation;

	@FindBy(xpath = "//*[@id=\"order-summary\"]/div/div[3]/table/tfoot/tr/td/span")
	WebElement checkoutPrice;

	@FindBy(id = "checkout_email")
	WebElement emailInputField;

	@FindBy(id = "checkout_shipping_address_first_name")
	WebElement firstNameInputField;

	@FindBy(id = "checkout_shipping_address_last_name")
	WebElement lastNameInputField;

	@FindBy(id = "checkout_shipping_address_address1")
	WebElement addressInputField;

	@FindBy(id = "checkout_shipping_address_city")
	WebElement cityInputField;

	@FindBy(id = "checkout_shipping_address_zip")
	WebElement postCodeInputField;

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/div[2]/div/form/div[2]/button")
	WebElement shippingButton;

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/div[1]/a/img")
	WebElement home;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean isContactCorrect() {
		return contactInformation.getText().toLowerCase().trim().equals("contact information");
	}

	public double getCheckoutPrice() {
		String s = checkoutPrice.getText().toLowerCase().trim().replaceAll("[^\\d+(.\\d+)?]", "");
		return Double.parseDouble(s);
	}

	public void setEmail(String email) {
		emailInputField.sendKeys(email);
	}

	public void setName(String first, String last) {
		firstNameInputField.sendKeys(first);
		lastNameInputField.sendKeys(last);
	}

	public void setLocation(String address, String city, String postal_code) {
		addressInputField.sendKeys(address);
		cityInputField.sendKeys(city);
		postCodeInputField.sendKeys(postal_code);
	}

	public ShippingPage goToShippingGuest(String email, String first, String last, String address, String city,
			String post) {
		this.setEmail(email);
		this.setName(first, last);
		this.setLocation(address, city, post);
		shippingButton.click();
		return new ShippingPage(driver);
	}

	public HomePage goHome() {
		home.click();
		return new HomePage(driver);
	}

}
