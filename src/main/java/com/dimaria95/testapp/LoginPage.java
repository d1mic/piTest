package com.dimaria95.testapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

	@FindBy(xpath = "//*[@id='customer_login']/div/h1")
	WebElement loginPageTitle;

	@FindBy(id = "CustomerEmail")
	WebElement emailField;

	@FindBy(id = "CustomerPassword")
	WebElement passField;

	@FindBy(xpath = "//*[@id=\"customer_login\"]/p[2]/input")
	WebElement signinButton;

	@FindBy(xpath = "//*[@id=\"customer_login\"]/div[2]/ul/li")
	WebElement errorMsg;

	@FindBy(id = "customer_register_link")
	WebElement registerButton;

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public String getLoginTitle() {
		return loginPageTitle.getText().toLowerCase().trim();
	}

	public void setCredentials(String email, String password) {
		emailField.sendKeys(email);
		passField.sendKeys(password);
	}

	public boolean isErrorMsgDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(errorMsg));
		return errorMsg.getText().toLowerCase().trim().equals("invalid login credentials.");
	}

	public MyAccountPage signIn(String email, String password) {
		this.setCredentials(email, password);
		signinButton.click();
		return new MyAccountPage(driver);
	}

}
