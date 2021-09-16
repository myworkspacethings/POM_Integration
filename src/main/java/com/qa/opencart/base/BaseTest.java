package com.qa.opencart.base;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;

public class BaseTest {

	public BasePage basePage;
	public Properties prop;
	public WebDriver driver;
	public LoginPage loginPage;
	public HomePage homePage;
	public ProductInfoPage productInfoPage;
	public RegisterPage registerPage;
	
	/*
	 * BasePage basepage=new BasePage();
	 * 
	 * To initialize the driver we need to initialize browser= chrome from config.properties file first.
	 * 	Initialize basepage.initProp(); and it will return Properties so store it in *Properties prop* reference
	 * 
	 * Properties prop=basepage.init_prop();
	 * Read the browser propery from config file and store it
	 * 
	 * Pass the same browserName reference here
	 * 
	 * To call the loginpage title and all methods create object of loginpage in BaseTest.java
	 */
	
	@BeforeTest
	public void setUp() {
		
		basePage=new BasePage();		
		prop=basePage.init_prop();                                      
		String browserName=prop.getProperty("browser"); 
																									 
		driver=basePage.init_driver(browserName);                 
		
		driver.get(prop.getProperty("url"));		
		loginPage= new LoginPage(driver);
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
