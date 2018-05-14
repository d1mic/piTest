package com.dimaria95.testapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage {
	
	
	@FindBy(xpath = "//*[@id=@*]/header/div/div/div[1]/div/a/img")
	WebElement homeButton;
	
	protected WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public HomePage goHome(){
		homeButton.click();
		return new HomePage(driver);
	}
}