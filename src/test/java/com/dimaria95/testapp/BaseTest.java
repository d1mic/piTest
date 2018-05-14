package com.dimaria95.testapp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

	protected WebDriver driver;

	public WebDriver initDriver(final MutableCapabilities capabilitie) throws MalformedURLException {
		if (driver == null) {
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilitie);
		}
		driver.get("https://thepihut.com/");
		return driver;
	}

	@BeforeClass(alwaysRun = true)
	public void setupTest() throws MalformedURLException {
		driver = initDriver(new FirefoxOptions());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("Before test");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		System.out.println("After test");
		if (driver != null)
			driver.quit();
	}

}
