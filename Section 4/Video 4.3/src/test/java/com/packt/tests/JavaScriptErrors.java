package com.packt.tests;

import java.util.List;

import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.packt.base.BaseTest;

public class JavaScriptErrors extends BaseTest {

	@Test
	public void javaScriptErrorsTest() {
		SoftAssert softAssert = new SoftAssert();

		// Opening page
		//driver.get("http://the-internet.herokuapp.com/javascript_error");
		driver.get("http://the-internet.herokuapp.com/login");
		System.out.println("Page opened!");

		// Verifying there are no JavaScript errors in console
		List<LogEntry> logs = getBrowserLogs();
		System.out.println("Logs: " + logs);

		for (LogEntry logEntry : logs) {
			if (logEntry.getLevel().toString().equals("SEVERE")) {
				softAssert.fail("Severe error: " + logEntry.getMessage());
			}
		}
		softAssert.assertAll();
	}
}
