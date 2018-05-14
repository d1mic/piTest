package com.dimaria95.testapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
	
	private static Properties propData = new Properties();
	protected WebDriver driver;
	protected String m_email,m_name,m_lastname,m_adress,m_city,m_code,m_pass;
	

	protected void loadProps() throws IOException {
		InputStream reader = new FileInputStream("src/test/resources/prop.properties");
		propData.load(reader);
		m_email = propData.getProperty("email");
		m_name = propData.getProperty("name");
		m_lastname = propData.getProperty("lastname");
		m_adress = propData.getProperty("address");
		m_city = propData.getProperty("city");
		m_code = propData.getProperty("code");
		m_pass = propData.getProperty("pass");
	}

	public WebDriver initDriver(final MutableCapabilities capabilitie) throws MalformedURLException {
		if (driver == null) {
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilitie);
		}
		driver.get("https://thepihut.com/");
		return driver;
	}

	@BeforeClass(alwaysRun = true)
	public void setupTest() throws IOException {
		this.loadProps();
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
