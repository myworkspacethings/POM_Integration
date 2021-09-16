package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.base.BasePage;
import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class LoginPage extends BasePage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//By Locators OR=Object Repository
	//Locators are private in nature you can also use public but here you are getting advanage you are achieving Encapsulation so no one can access locators unnecessary
	private By emailId=By.id("input-email");
	private By password=By.id("input-password");
	private By loginButton=By.xpath("//*[@id='content']/div/div[2]/div/form/input");
	private By forgotPasswordlink=By.linkText("Forgotten Password");
	private By wishListLink=By.xpath("//*[@id='wishlist-total']/span");
	private By checkOutLink=By.cssSelector("i.fa.fa-share");
	private By registerLink= By.linkText("Register");
	
	//Constructor of the page class
	public LoginPage(WebDriver driver) {
		this.driver=driver;	
		
		elementUtil= new ElementUtil(driver);
	}
	
	//Page actions: features of the page in the form of methods
	public String getLoginPageTitle() {
		return elementUtil.waitForPageTitleToBePresent(5, Constants.LOGIN_PAGE_TITLE);
	}
	
	public boolean isForgotPasswordLinkExist() {
//		return driver.findElement(forgotPasswordlink).isDisplayed();
		return elementUtil.doIsDisplayed(forgotPasswordlink);
	}
	
	public boolean isWishListLinkExist() {
//		return driver.findElement(wishListLink).isDisplayed();
		return elementUtil.doIsDisplayed(wishListLink);
	}
	
	public boolean isCheckOutLinkExist() {
//		return driver.findElement(checkOutLink).isDisplayed();
		return elementUtil.doIsDisplayed(checkOutLink);
	}
	
	public HomePage doLogin(String un, String pwd) {
//		driver.findElement(emailId).sendKeys(un);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginButton).click();		

		elementUtil.doSendKeys(emailId, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		
		return new HomePage(driver);
		
	}
	
	public RegisterPage navigateToRegisterPage() {
		elementUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
}
