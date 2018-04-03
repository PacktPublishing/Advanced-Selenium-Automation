package com.herokuapp;

import java.util.HashMap;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.packt.base.BasePageObject;

public class SecurePage extends BasePageObject {
	
	By logOutButtonLocator = By.xpath("//a[@class='button secondary radius']");

	public SecurePage(WebDriver driver, HashMap<String, String> testConfig, Logger log) {
		super(driver, testConfig, log);
	}


	public void waitForSecurePage(long millis) {
		log.info("Waiting for secure page");
		waitForElementPresent(logOutButtonLocator, 15);
	}


	public boolean isLogOutButtonVisible() {
		return find(logOutButtonLocator).isDisplayed();
	}

}
