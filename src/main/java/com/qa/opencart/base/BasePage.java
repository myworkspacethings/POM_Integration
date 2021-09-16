package com.qa.opencart.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.util.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();   //u need to use set and get with thread local
	
	/**
	 * This method is used to return driver on the basic of given user input
	 * 
	 * @param browserName
	 * @return webdriver driver
	 */
	public WebDriver init_driver(String browserName) {
	
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));         //set the driver

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

		} else if (browserName.equalsIgnoreCase("safari")) {
//			driver = new SafariDriver();
			tlDriver.set(new SafariDriver());

		} else {
			System.out.println("Please pass correct value" + browserName);
		}
//		driver.manage().deleteAllCookies();
		getDriver().manage().deleteAllCookies();             //use instead of driver
//		return driver;
		return getDriver();
	}
	
	/**
	 * getDriver using ThreadLocal
	 * 
	 * @return
	 */
	public static synchronized WebDriver getDriver() {								//sync for sync acorss driver and pick only 1 test 
		return tlDriver.get();																				//get driver
	}
	
	
	/**
	 * This method is used to load properties from config.properties file 
	 * 
	 * @return It returns  Properties prop reference
	 */

	public Properties init_prop() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("C:\\Users\\Dell\\EclipseWorkspace\\POMSessions\\src\\main\\java\\com\\qa\\config\\Config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	/*
	 * This method is used to take the screenshot
	 * It will return the path of screenshot
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
    