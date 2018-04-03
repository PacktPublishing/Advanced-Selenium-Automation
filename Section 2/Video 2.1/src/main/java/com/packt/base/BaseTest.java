package com.packt.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

	protected WebDriver driver;


	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	protected void setUp(@Optional("IE") String browser) {
		// Creating driver
		System.out.println("[Setting up driver: " + browser + "]");
		// some code here to change browser

		//System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		//driver = new ChromeDriver();

		//System.setProperty("webdriver.ie.driver", "src/main/resources/IEDriverServer.exe");
		//driver = new InternetExplorerDriver();

		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
		driver = new FirefoxDriver();
	}


	@AfterMethod(alwaysRun = true)
	protected void tearDown() {
		// Closing driver
		System.out.println("[Closing driver]");
		driver.quit();
	}


	@DataProvider(name = "negativeLogInData")
	public static Object[][] negativeLogInData() {
		return new Object[][] {
				{ "IncorrectUsername", "SuperSecretPassword!", "Your username is invalid!" },
				{ "tomsmith", "IncorrectPassword", "Your password is invalid!" }
		};
	}

}
