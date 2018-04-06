package com.packt.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.packt.base.BaseTest;

public class Troubleshooting extends BaseTest {

	@Test
	public void waitTest() {
		// Opening page
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		log.info("Page opened!");

		// Clicking start button
		WebElement startButton = driver.findElement(By.xpath("//button"));
		startButton.click();

		// Waiting for Hello World! text
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));

		// Verifying Hello World text
		WebElement finishText = driver.findElement(By.id("finish"));
		String text = finishText.getText();
		Assert.assertTrue(text.equals("Good Bye World!"), "'Good Bye World! text is not present on the page.'");
	}

	@Test
	public void visibilityTest() {
		// Opening page
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");
		log.info("Page opened!");

		// Clicking start button
		WebElement startButton = driver.findElement(By.xpath("//button"));
		log.info("Is Start button visible before click: " + startButton.isDisplayed());
		startButton.click();

		// Waiting for Hello World! text
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));

		// Verifying Hello World text
		WebElement finishText = driver.findElement(By.id("finish"));
		String text = finishText.getText();
		Assert.assertTrue(text.equals("Hello World!"), "'Hello World! text is not present on the page.'");

		log.info("Is Start button visible after click: " + startButton.isDisplayed());
	}

	@Test
	public void staleElementTest() {
		// Opening page
		driver.get("http://the-internet.herokuapp.com/notification_message_rendered");
		log.info("Page opened!");

		WebElement link = driver.findElement(By.linkText("Click here"));
		link.click();

		// Waiting for message
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));

		WebElement message = driver.findElement(By.id("flash"));
		log.info("Message: " + message.getText());

		link = driver.findElement(By.linkText("Click here"));
		link.click();
		WebElement message2 = driver.findElement(By.id("flash"));
		log.info("Message: " + message2.getText());

		link = driver.findElement(By.linkText("Click here"));
		link.click();
		WebElement message3 = driver.findElement(By.id("flash"));
		log.info("Message: " + message3.getText());

		link = driver.findElement(By.linkText("Click here"));
		link.click();
		WebElement message4 = driver.findElement(By.id("flash"));
		log.info("Message: " + message4.getText());
	}

	@Test
	public void otherTest() {
		// Opening page
		driver.get("http://the-internet.herokuapp.com/hovers");
		log.info("Page opened!");

		sleep(3000);
		// JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		// jsExecutor.executeScript("window.scrollBy(0,250)", "");
		// sleep(3000);
		// WebElement table = driver.findElement(By.id("large-table"));
		// jsExecutor.executeScript("arguments[0].scrollIntoView();", table);
		// sleep(3000);

		WebElement picture = driver.findElement(By.xpath("//div[@class='figure']"));
		WebElement link = driver.findElement(By.xpath("//a[contains(text(),'View profile')]"));

		Actions action = new Actions(driver);
		action.moveToElement(picture).build().perform();
		sleep(3000);
		action.moveToElement(link).click().build().perform();
		sleep(3000);
	}

	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
