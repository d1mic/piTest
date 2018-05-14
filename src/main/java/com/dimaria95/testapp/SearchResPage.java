package com.dimaria95.testapp;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResPage extends BasePage {

	@FindBy(xpath = "//*[@id=\"search-results\"]/main/div/div[2]/header/h1/span[2]")
	WebElement titleSearch;

	@FindBy(xpath = "//*[@id=\"isp_search_results_container\"]/li[*]/div[2]/div[2]/span")
	List<WebElement> prices;

	@FindBy(how = How.CLASS_NAME, using = "isp_facet_value_name_category")
	List<WebElement> categories;

	@FindBy(xpath = "//*[@id=\"isp_left_container_facets\"]/div/div[3]/div")
	WebElement inStock;

	public SearchResPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String getSearchTitle() {
		return titleSearch.getText().toLowerCase().trim();
	}

	public String getResultText() {
		WebElement resultText = driver.findElement(By.id("isp_results_search_text"));
		return resultText.getText().toLowerCase().trim();
	}

	public int getNumOfResults() {
		WebElement resultNumber = driver.findElement(By.id("isp_results_summary_total_results"));
		return Integer.parseInt(resultNumber.getText().trim());
	}

	public WebElement getNumOfResultsEl() {
		WebElement resultNumber = driver.findElement(By.id("isp_results_summary_total_results"));
		return resultNumber;
	}

	public String selectCheapPrice() {
		WebElement firstPriceTag = driver.findElement(By.className("isp_facet_value"));
		String priceText = firstPriceTag.getText();
		firstPriceTag.click();
		return priceText;
	}

	public double getPriceMinimum(String s) {
		String min = s.substring(s.indexOf("£") + 1, s.indexOf(" ", s.indexOf("£") + 1));
		return Double.parseDouble(min);
	}

	public double getPriceMaximum(String s) {
		String max = s.substring(s.lastIndexOf("£") + 1, s.indexOf(" ", s.lastIndexOf("£") + 1));
		return Double.parseDouble(max);
	}

	public boolean arePricesInRange(double min, double max) {
		for (WebElement price : prices) {
			String numStr = price.getText().toLowerCase().trim().replaceAll("[^\\d+(.\\d+)?]", "");
			double current_price = Double.parseDouble(numStr);
			if (current_price > max || current_price < min)
				return false;
		}
		return true;
	}

	public void selectInStockItems() {
		inStock.click();
	}

	public void selectCategory(int category) {
		WebElement cat = categories.get(category);
		cat.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.stalenessOf(cat));
	}

	public String getCategoryNav(int category) {
		String cat = categories.get(category).getText().toLowerCase();
		return cat.substring(0, cat.lastIndexOf(" "));
	}

	public String getCategoryTitle() {
		String summary = driver.findElement(By.id("isp_results_summary")).getText().trim();
		String[] summarySplit = summary.split("\"");
		return summarySplit[summarySplit.length - 1].toLowerCase();
	}

}
