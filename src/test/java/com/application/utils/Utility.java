package com.application.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.android.AndroidDriver;

public class Utility {

	public AndroidDriver<MobileElement> driver;

	public void waitForPageToLoad(String element) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
	}

	public void waitForElementToDisAppear(String id) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(id)));
	}

	public void waitForElementToLoad(String element) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public void swipeLeftUntilTextExists(String expected) {
		do {
			swipeLeft();
		} while (!driver.getPageSource().contains(expected));
	}

	public void swipeRight() {
		Dimension size = driver.manage().window().getSize();
		int startx = (int) (size.width * 0.9);
		int endx = (int) (size.width * 0.20);
		int starty = size.height / 2;
		driver.swipe(startx, starty, endx, starty, 5000);
	}

	public void swipeLeft() {
		Dimension size = driver.manage().window().getSize();
		int startx = (int) (size.width * 0.8);
		int endx = (int) (size.width * 0.20);
		int starty = size.height / 2;
		driver.swipe(startx, starty, endx, starty, 1000);
	}

	public void scrollDirection(MobileElement Id, SwipeElementDirection arg) {
		MobileElement e = Id;
		e.swipe(arg, 1000);
	}

	// screen shots Function
	public static String captureScreen(AndroidDriver<MobileElement> driver) {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = "./Screenshot_20/" + new Random().nextInt(1000) + ".png";
		try {
			FileUtils.copyFile(src, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

	// Swipe Up checking the elements After Year 1990 check condition
	public static boolean verticalSwipe(AndroidDriver<MobileElement> driver, By by) {
		int flag = 0;
		Dimension dim = driver.manage().window().getSize();
		int height = dim.getHeight();
		// int width=dim.getWidth();
		// int x=width/2;
		int endy = (int) (height * 0.80);
		// int starty=(int)(height*0.20);
		for (int i = 0; i <= 10; i++) {
			driver.swipe(0, 0, 0, endy + 300, 5000);
			if (i >= 4) {
				List<MobileElement> list = driver.findElements(by);
				if (list.size() > 0) {
					flag = 1;
					break;
				} else {
					flag = 0;
				}
			}

		}
		if (flag == 1)
			return true;
		else
			return false;

	}

	// Swipe Down
	@SuppressWarnings("unused")
	public static void verticalSwipeDown(AndroidDriver<MobileElement> driver) {
		try {
			int flag = 0;
			Dimension dim = driver.manage().window().getSize();
			int height = dim.getHeight();
			int width = dim.getWidth();
			int x = width / 2;
			int endy = (int) (height * 0.20);
			int starty = (int) (height * 0.80);
			driver.swipe(x, starty, x, endy, 1000);
			System.out.println("SUCCESS: Vertical Swipe Down ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("FAILURE: Vertical Swipe Down ");

		}

	}

	// Android Date Picker For Date Selection
	public static void month(AndroidDriver<MobileElement> driver, String screenMonth, String given_Month)
			throws InterruptedException {
		String deviceMonth = screenMonth;
		deviceMonth = deviceMonth.split(",")[1];
		// deviceMonth=deviceMonth.trim();
		deviceMonth = deviceMonth.substring(1, 4);
		ArrayList<String> month = new ArrayList<String>();
		month.add("Jan");
		month.add("Feb");
		month.add("Mar");
		month.add("Apr");
		month.add("May");
		month.add("Jun");
		month.add("Jul");
		month.add("Aug");
		month.add("Sep");
		month.add("Oct");
		month.add("Nov");
		month.add("Dec");

		int diff = month.indexOf(deviceMonth) - month.indexOf(given_Month);
		if (month.indexOf(deviceMonth) > month.indexOf(given_Month)) {
			if (diff < 0)
				diff = (-diff);
			for (int i = 0; i < diff; i++) {
				driver.findElementByXPath("//*[@resource-id='android:id/prev']").click();
				captureScreen(driver);
				Thread.sleep(1000);
			}
		} else if (month.indexOf(deviceMonth) < month.indexOf(given_Month)) {
			if (diff < 0)
				diff = (-diff);
			for (int i = 0; i < diff; i++) {
				driver.findElementByXPath("//*[@resource-id='android:id/tNext']").click();
				captureScreen(driver);
				Thread.sleep(1000);
			}
		} else {

		}

	}

	public static String xpathGenerator(String elementName) {
		return "//*[@text='" + elementName + "']";

	}

}
