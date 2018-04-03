package com.packt.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserDriverFactory {

	public static WebDriver createDriver(String browser) {
		System.out.println("[Setting up driver: " + browser + "]");
		WebDriver driver = null;
		browser = browser.toLowerCase();

		// Creating driver
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;

		case "ie":
			System.setProperty("webdriver.ie.driver", "src/main/resources/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		}

		return driver;
	}

}
