package com.packt.base;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

	protected WebDriver driver;
	protected HashMap<String, String> testConfig = new HashMap<String, String>();
	protected Logger log;


	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	protected void setUp(@Optional("chrome") String browser, ITestContext ctx) {
		BrowserDriverFactory factory = new BrowserDriverFactory(browser);
		driver = factory.createDriver();
		testConfig.put("browser", browser);

		String testName = ctx.getCurrentXmlTest().getName();
		log = LogManager.getLogger(testName);
	}


	@AfterMethod(alwaysRun = true)
	protected void tearDown() {
		// Closing driver
		log.info("[Closing driver]");
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
