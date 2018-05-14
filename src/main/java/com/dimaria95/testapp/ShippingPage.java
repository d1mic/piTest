package com.dimaria95.testapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShippingPage extends BasePage {

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/div[1]/a/img")
	WebElement home;

	@FindBy(id = "main-header")
	WebElement shippingMethodTittle;

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/div[2]/div/form/div[1]/div[1]/div/div/div[1]/div[1]/div[2]")
	WebElement email;

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/div[2]/div/form/div[2]/button")
	WebElement paymentButton;

	public ShippingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean checkPageTitle() {
		return shippingMethodTittle.getText().toLowerCase().trim().equals("shipping method");
	}

	public boolean checkEmail(String s) {
		return s.equals(email.getText().toLowerCase().trim());
	}

	public boolean checkPaymentAvailability() {
		return paymentButton.getText().toLowerCase().trim().equals("continue to payment method");
	}

	@Override
	public HomePage goHome() {
		home.click();
		return new HomePage(driver);
	}

}
