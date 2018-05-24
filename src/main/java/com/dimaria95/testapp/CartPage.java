package com.dimaria95.testapp;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {

	@FindBy(how = How.CLASS_NAME, using = "section-header__title")
	WebElement cartTitle;

	@FindBy(how = How.CLASS_NAME, using = "price")
	List<WebElement> prices;

	@FindBy(xpath = "//*[@id=\"your-shopping-cart\"]/header/div/div/div[2]/div/ul/li[4]/a/span[2]")
	WebElement counter;

	@FindBy(xpath = "//*[@id=\"your-shopping-cart\"]/main/div/div[1]/form/div[3]/div[2]/input[2]")
	WebElement checkoutButton;

	public CartPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean checkCartTitle() {
		String title = cartTitle.getText().toLowerCase().trim();
		return title.equals("shopping cart");
	}

	public double getTotalPrice() {
		String totalPrice = "";
		for (WebElement webElement : prices) {
			String priceText = webElement.getText().toLowerCase().trim();
			if (priceText.contains("payment of")) {
				totalPrice = priceText.substring(priceText.indexOf("of") + 4, priceText.indexOf("will")).trim();
				break;
			}
		}
		return Double.parseDouble(totalPrice);
	}

	public void removeAllItems() {

		int number = driver
				.findElements(By
						.xpath("//*[@id=\"your-shopping-cart\"]/main/div/div[1]/form/div[1]/table/tbody/tr[*]/td[3]/a"))
				.size();
		while (number > 0) {
			WebElement rb = driver.findElement(
					By.xpath("//*[@id=\"your-shopping-cart\"]/main/div/div[1]/form/div[1]/table/tbody/tr[*]/td[3]/a"));
			rb.click();
			number--;
		}
	}

	public boolean isEmptyMsg() {
		String msg = driver.findElement(By.xpath("//*[@id=\"your-shopping-cart\"]/main/div/div[1]/p[1]")).getText();
		return msg.toLowerCase().trim().equals("your cart is currently empty.");
	}

	public int getCounter() {
		if (counter.getText().isEmpty())
			return 0;
		else
			return Integer.parseInt(counter.getText());
	}

	public CheckoutPage goToCheckout() {
	
		checkoutButton.click();
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		//wait.until(ExpectedConditions.invisibilityOf(cartTitle));
		
		if (driver.findElement(By.xpath("//*[@id=\"bold-modal-first__window\"]/div[2]/a[2]")).isDisplayed()) {
			System.out.println("Promo page :( ");
			driver.findElement(By.xpath("//*[@id=\"bold-modal-first__window\"]/div[2]/a[2]")).click();
		}
		return new CheckoutPage(driver);
	}

}
