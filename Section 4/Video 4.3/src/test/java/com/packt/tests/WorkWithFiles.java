package com.packt.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.packt.base.BaseTest;

public class WorkWithFiles extends BaseTest {

	@Test
	public void fileUploadTest() {
		// Opening page
		driver.get("http://the-internet.herokuapp.com/upload");
		log.info("Page opened!");

		sleep(3000);

		// maximize window
		driver.manage().window().maximize();

		sleep(1000);

		// Selecting file
		//String filePath = "C:\\Users\\Dee\\Downloads\\some-file.txt";
		String filePath = System.getProperty("user.dir") + "//src//main//resources//log4j2.xml";

		WebElement uploadElement = driver.findElement(By.id("file-upload"));
		uploadElement.sendKeys(filePath);
		log.info("File selected");
		sleep(2000);

		// Uploading file
		WebElement uploadButton = driver.findElement(By.id("file-submit"));
		uploadButton.click();

		String source = driver.getPageSource();
		Assert.assertTrue(source.contains("File Uploaded!"), "File is not uploaded!");
		
		log.info("File uploaded");
		sleep(5000);
	}

	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
