package com.application.pages;

import java.util.List;

import org.apache.log4j.Logger;

import com.application.exception.GenericFunctionException;
import com.application.exception.ReusableFunctionException;
import com.application.utils.GenericFunctions;
import com.application.utils.PageObject;

import io.appium.java_client.MobileElement;

public class Search {
	Logger logger = Logger.getLogger(Search.class);
	GenericFunctions oGen = null;
	public void searchItem(String itemName) throws ReusableFunctionException {
		List<MobileElement> elements = null;
		try {
		oGen = new GenericFunctions();
		oGen.findAndClickById(PageObject.searchBox);
		oGen.findAndSendById(PageObject.searchItem, itemName);
		oGen.findAndClickById(PageObject.selectFirstSearchResult);
		elements =oGen.findByElementById(PageObject.items);
		oGen.clickByItemIndexFromList(elements, 1);
		oGen.findAndClickById(PageObject.buyButton);
		
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
			throw new ReusableFunctionException(e);
		} catch (GenericFunctionException e) {
			logger.error(e.getMessage());
			throw new ReusableFunctionException(e);
		}
		
		
		
	
	}
	
}
