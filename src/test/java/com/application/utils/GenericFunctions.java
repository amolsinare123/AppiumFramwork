package com.application.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.application.exception.GenericFunctionException;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class GenericFunctions {

	/*
	 ******************************* Contains Reusable App Specific Functions *****************************
	 */
	public static AndroidDriver<MobileElement> driver;
	Logger logger = Logger.getLogger(GenericFunctions.class);

	/*
	 * Method to scroll in the application until required text is found
	 */
	public void scrollIntoView(String element) throws GenericFunctionException {
		try {
			Thread.sleep(60000);
			driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\""
							+ element + "\").instance(0))"));
			// driver.findElementByAndroidUIAutomator(
			// "new UiScrollable(new
			// UiSelector().scrollable(true).instance(0)).scrollIntoView(text(\"" + element
			// + "\"));");
			logger.info("SUCCESS: The page is scrolled to the mentioned text " + element);
			// Utility.captureScreen(driver);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			logger.error("FAILURE: The page cannot be scrolled to " + element);
			// Utility.captureScreen(driver);
			throw new GenericFunctionException(e);
		} catch (InterruptedException e) {
			logger.error("FAILURE: The page cannot be scrolled to " + element);
			// Utility.captureScreen(driver);
			throw new GenericFunctionException(e);
		}
	}

	/*
	 * Method to locate the presence of an element with XPath.
	 */
	public void objLocateXpath(String xPath) throws GenericFunctionException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			logger.info("Waiting for the element " + xPath + "to be located");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
			Assert.assertTrue(driver.findElementByXPath(xPath).isDisplayed());
			// Utility.captureScreen(driver);
			logger.info("SUCCESS: " + xPath + "presence is located successfuly.");
		} catch (Exception e) {
			logger.error(xPath + " cannot  be located");
			// Utility.captureScreen(driver);
			throw new GenericFunctionException(e);
		}

	}

	/*
	 * Method to wait until an element is found with its Class Name
	 */
	public void objLocateClass(String className) throws GenericFunctionException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			logger.info("Waiting for the element " + className + "to be located");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
			// Utility.captureScreen(driver);
			logger.info("SUCCESS: " + className + " presence is located successfuly.");
		} catch (Exception e) {
			logger.error("FAILURE: " + className + " cannot  be located with given Classname.");
			// Utility.captureScreen(driver);
			throw new GenericFunctionException(e);
		}

	}

	/*
	 * Method to wait until an element is found with its Class Name and Click
	 */
	public void objClickClass(String className) throws GenericFunctionException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 25);
			logger.info("Waiting for the element " + className + "to be located");
			wait.until(ExpectedConditions.elementToBeClickable(By.className(className))).click();
			// Utility.captureScreen(driver);
			logger.info("SUCCESS: " + className + " is found and clicked successfuly.");
		} catch (Exception e) {
			logger.error("FAILURE: " + className + " cannot be located click it.");
			// Utility.captureScreen(driver);
			throw new GenericFunctionException(e);
		}

	}

	/*
	 * Method to wait until an element is found and Click on it with XPath
	 */
	public void objClickXpath(String xPath) throws GenericFunctionException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 25);
			logger.info("Waiting for the element " + xPath + "to be found");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath))).click();
			// Utility.captureScreen(driver);
			logger.info("SUCCESS: " + xPath + " is found and clicked successfuly.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("FAILURE: " + xPath + " cannot  be found to click");
			// Utility.captureScreen(driver);
			throw new GenericFunctionException(e);
		}

	}

	/*
	 * Method to wait until an element is found and Click on it with Link Text
	 */
	public void objClickText(String Text) throws GenericFunctionException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 25);
			logger.info("Waiting for the element " + Text + "to be found");
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(Text))).click();
			// Utility.captureScreen(driver);
			logger.info("SUCCESS: " + Text + " is found and clicked successfuly.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("FAILURE: " + Text + " cannot  be found to click");
			// Utility.captureScreen(driver);
			throw new GenericFunctionException(e);
		}

	}

	/*
	 * Method to find elements by Simple Classname
	 */
	public List<MobileElement> findByClassName(String className) throws GenericFunctionException {
		List<MobileElement> list = new ArrayList<MobileElement>();
		try {
			logger.info("Waiting for the element " + className + "to be located");
			list = driver.findElementsByClassName(className);
			logger.info("The size of the requested list is :" + list.size());
			logger.info("The requested list contains " + list);
			// Utility.captureScreen(driver);
			logger.info("SUCCESS: " + className + " is found and retrieved to List.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("FAILURE: " + className + " cannot be found with Classname");
			// Utility.captureScreen(driver);
			throw new GenericFunctionException(e);
		}
		return list;
	}

	/*
	 * Method to find element by Simple Classname with first single index
	 */
	public void findAndGetByClassIndex(String className, int index0) throws GenericFunctionException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
			logger.info("Waiting for the element " + className + "to be located");
			driver.findElementsByClassName(className).get(index0);
			logger.info("SUCCESS: " + className + " is found and " + index0 + " is retrieved successfuly.");
			// Utility.captureScreen(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("FAILURE: " + className + " cannot be located with given index");
			// Utility.captureScreen(driver);
			throw new GenericFunctionException(e);
		}

	}

	/*
	 * Method to find element by Simple Classname and pass two values for its first
	 * two indexes
	 */
	public void findAndSendByClassIndex(String className, String index0, String index1)
			throws GenericFunctionException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			logger.info("Waiting for the element " + className + "to be located");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
			driver.findElementsByClassName(className).get(0).sendKeys(index0);
			driver.findElementsByClassName(className).get(1).sendKeys(index1);
			logger.info(
					"SUCCESS: " + className + " is found and " + index0 + "," + index1 + " are entered successfuly.");
			// Utility.captureScreen(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("FAILURE: " + className + " cannot be located to sendkeys to indexes");
			// Utility.captureScreen(driver);
			throw new GenericFunctionException(e);
		}

	}

	/*
	 * Method to find Element with ClassName and sendkeys
	 */
	public void findAndSendByClassName(String className, String key) throws GenericFunctionException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			logger.info("Waiting for the element " + className + "to be located");
			wait.until(ExpectedConditions.elementToBeClickable(By.className(className))).sendKeys(key);
			logger.info("SUCCESS: " + className + " is found and" + key + " is entered successfuly.");
			// Utility.captureScreen(driver);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("FAILURE: " + className + " cannot be located to sendkeys with Classname");
			// Utility.captureScreen(driver);
			throw new GenericFunctionException(e);
		}

	}

	/*
	 * Method to find Element with XPath and sendkeys
	 */
	public void findAndSendByXpath(String xPath, String key) throws GenericFunctionException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			logger.info("Waiting for the element " + xPath + "to be found");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath))).sendKeys(key);
			// Utility.captureScreen(driver);
			logger.info("SUCCESS: " + xPath + " is found and clicked successfuly.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("FAILURE: " + xPath + " cannot  be found to click");
			// Utility.captureScreen(driver);
			throw new GenericFunctionException(e);
		}

	}

	public void findAndClickById(String id) throws GenericFunctionException {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 50);
			logger.info("Waiting for the element " + id + "to be found");
			wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).click();
			// Utility.captureScreen(driver);
			logger.info("SUCCESS: " + id + " is found and clicked successfuly.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("FAILURE: " + id + " cannot  be found to click");
			// Utility.captureScreen(driver);
			throw new GenericFunctionException(e);
		}

	}

	public void findAndSendById(String id, String string) throws GenericFunctionException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 25);
			logger.info("Waiting for the element " + id + " to be found");
			wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).sendKeys(string);

			logger.info("SUCCESS: " + id + " is found and" + string + " is entered successfuly.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("FAILURE: " + id + " cannot  be found to click");
			// Utility.captureScreen(driver);
			throw new GenericFunctionException(e);
		}

	}

	public List<MobileElement> findByElementById(String id) throws GenericFunctionException {

		List<MobileElement> list = new ArrayList<MobileElement>();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 25);
			logger.info("Waiting for the element " + id + "to be located");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
			list = driver.findElementsById(id);
			logger.info("The size of the requested list is :" + list.size());
			logger.info("The requested list contains " + list);
			// Utility.captureScreen(driver);
			logger.info("SUCCESS: " + id + " is found and retrieved to List.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("FAILURE: " + id + " cannot be found with Classname");
			// Utility.captureScreen(driver);
			throw new GenericFunctionException(e);
		}
		return list;

	}

	public void clickByItemIndexFromList(List<MobileElement> elements, int i) {

		for (MobileElement mobileElement : elements) {
			mobileElement.click();
		}

	}

	public String findAndGetText(String locator, String identifier) {
		MobileElement element = null;
		String value = null;
		switch (identifier) {
		case "id":
			logger.info("Waiting for the element " + identifier + "to be found");
			element = driver.findElement(By.id(identifier));
			// Utility.captureScreen(driver);
			logger.info("SUCCESS: " + identifier + " is found and clicked successfuly.");
			value = element.getText();
			break;

		default:
			break;
		}
		return value;

	}

	/*
	 * Method to validate displayed Text with Expected text provided.
	 */
	/*
	 * public void validateText(String className, int index, String expectedText) {
	 * try { System.out.println("Waiting for the element " + className +
	 * "to be located"); String actualText =
	 * driver.findElementsByClassName(className).get(index).getText();
	 * Assert.assertTrue("The Expected tError message is not displayed.",
	 * actualText.contains(expectedText)); logger.info("SUCCESS: " + actualText +
	 * " is displayed. Validation is Successful."); } catch (Exception e) { // TODO
	 * Auto-generated catch block logger.error("FAILURE: " + expectedText +
	 * " cannot be located with given Classname."); //Utility.captureScreen(driver);
	 * throw new GenericFunctionException(e); }
	 * 
	 * }
	 */

}
