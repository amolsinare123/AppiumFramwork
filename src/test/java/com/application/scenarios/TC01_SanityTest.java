package com.application.scenarios;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.application.exception.ReusableFunctionException;
import com.application.pages.Login;
import com.application.pages.Search;
import com.application.utils.Constants;
import com.application.utils.DriverInit;
import com.application.utils.GenericFunctions;
import com.application.utils.JSonParser;
import com.application.utils.PageObject;
import com.application.utils.Utility;
import com.application.pages.Review;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class TC01_SanityTest {

	private static AndroidDriver<MobileElement> driver;

	@Test
	public void sanity() throws InterruptedException, IOException {
Logger logger = Logger.getLogger(TC01_SanityTest.class);
		/*
		 **************************** TC 001 - Sanity Test*******************************
		 */
Login login = null;
Search search = null;
Review review = null;
JSONArray jsonArray = null;
JSONObject jsonObject = null;
		try {
			driver = new DriverInit().getDriver();
			GenericFunctions.driver = driver;
			logger.debug("Android driver initialized successfully");
			jsonArray = JSonParser.getUserData("data");
			if(jsonArray!=null) {
				for (int i = 0; i < jsonArray.size(); i++) {
					
				jsonObject = (JSONObject) jsonArray.get(i);
			 login = new Login();
			login.login((String)jsonObject.get(Constants.userName),(String)jsonObject.get(Constants.password));
			logger.debug("Login to application successfully");
			 search = new Search();
			search.searchItem((String)jsonObject.get(Constants.productName));
			logger.debug("Product searched successfully");
			  review = new Review();
			review.validateItemNamebyId(PageObject.itemNameOnReview,  (String)jsonObject.get(Constants.productName));
			Utility.captureScreen(driver);
			}}
		} catch (ReusableFunctionException e) {
			logger.error(e.getMessage());
		}
	}

}
