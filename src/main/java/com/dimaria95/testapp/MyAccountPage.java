package com.dimaria95.testapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage extends BasePage {

	@FindBy(xpath = "//*[@id=\"account\"]/main/div/div/div[2]/div[2]/h5")
	WebElement name;

	@FindBy(xpath = "//*[@id=\"account\"]/main/div/div/div[2]/div[1]/h4")
	WebElement orderHistoryTitle;

	@FindBy(xpath = "//*[@id=\"account\"]/header/div/div/div[2]/div/ul/li[4]")
	WebElement logoutButton;

	public MyAccountPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String getName() {
		return name.getText().toLowerCase().trim();
	}

	public boolean checkOrderHistory() {
		String title = orderHistoryTitle.getText().toLowerCase().trim();
		if (title.equals("order history")) {
			return true;
		}
		return false;
	}

	public String getLogOutButton() {
		return logoutButton.getText().toLowerCase().trim();
	}

	public HomePage logOut() {
		logoutButton.click();
		return new HomePage(driver);
	}

}
