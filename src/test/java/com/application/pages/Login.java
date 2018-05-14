package com.application.pages;

import org.apache.log4j.Logger;

import com.application.exception.GenericFunctionException;
import com.application.exception.ReusableFunctionException;
import com.application.utils.GenericFunctions;
import com.application.utils.PageObject;

public class Login {
	Logger logger = Logger.getLogger(Login.class);
	GenericFunctions oGen = null;
	
	public void login(String userName, String password) throws ReusableFunctionException {
		oGen = new GenericFunctions();
		try {
			oGen.findAndClickById(PageObject.signIn);
			oGen.findAndSendById(PageObject.userName, userName);
			oGen.findAndSendById(PageObject.password, password);
			oGen.findAndClickById(PageObject.signInButton);
			oGen.findAndClickById(PageObject.demyToSaveDetails);
		} catch (GenericFunctionException e) {
			logger.error(e.getMessage());
			throw new ReusableFunctionException(e);
		}
	//	oGen.findAndClickById(PageObject.);
	}
	
}
