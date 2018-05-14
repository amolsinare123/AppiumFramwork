package com.application.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class DriverInit {

	DesiredCapabilities cap = new DesiredCapabilities();
	public AndroidDriver<MobileElement> mobiledriver;
	public static Properties prop = new Properties();
	static InputStream input = null;
	Utility util = new Utility();

	@BeforeClass
	public AndroidDriver<MobileElement> getDriver() throws IOException, InterruptedException {

		File appDir = new File("src/main/resources");
		File app = new File(appDir, "com.ebay.mobile_v5.20.1.1-126_Android-5.0.apk");
		cap.setCapability("noReset", "true");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "ZY22235XSL");
		cap.setCapability("deviceVersion", "7.0");
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		mobiledriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		Utility.captureScreen(mobiledriver);
		Thread.sleep(2000);
		return mobiledriver;
	}



}
