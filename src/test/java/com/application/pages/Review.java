package com.application.pages;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;

import com.application.exception.ReusableFunctionException;
import com.application.utils.GenericFunctions;

public class Review {
	GenericFunctions oGen = null;
	Logger logger = Logger.getLogger(Review.class);
public void validateItemNamebyId(String locator,  String expected) throws ReusableFunctionException {
	String response = null;
	try {
	response = oGen.findAndGetText(locator, "id");
	assertEquals(true,response.contains(expected));
	}catch(Exception e) {
		logger.error(e.getMessage());
		throw new ReusableFunctionException(e);
	}
}
}
