package com.packt.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

	protected WebDriver driver;


	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	protected void setUp(@Optional("Chrome") String browser) {
		driver = BrowserDriverFactory.createDriver(browser);
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
