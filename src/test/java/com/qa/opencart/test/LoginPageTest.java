package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.listeners.ExtentReportListener;
import com.qa.opencart.util.Constants;

//@Listeners(ExtentReportListener.class)                    //no need of geneating extent reporrt for every class so commenting
@SuppressWarnings("unused")
public class LoginPageTest extends BaseTest {

	@Test(priority=1)
	public void verifyLoginPageTitleTest() {
		//BaseTest will take care of loginPage object creation
		String title=loginPage.getLoginPageTitle();   
		System.out.println("Page title is : "+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifyForgotPasswordLinkTest() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}
	
	@Test(priority=3)
	public void verifyWishListLinkTest() {
		loginPage.isWishListLinkExist();
	}
	
	@Test(priority=4)
	public void verifyCheckOutLinkTest() {
		Assert.assertTrue(loginPage.isCheckOutLinkExist());
//		loginPage.isCheckOutLinkExist();
	}
		
	@Test(priority=5)
	public void loginTest() {
		//prop reference is coming from BaseTest which is already extended by loginpagetest
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}	
}
