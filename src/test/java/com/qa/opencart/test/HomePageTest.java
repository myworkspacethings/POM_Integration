package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.util.Constants;


public class HomePageTest extends BaseTest {

	@BeforeClass
	public void homePageSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));		
	}
	
	@Test(priority=1)
	public void homePageTitleTest() {
		String title=homePage.getHomePageTitle();
		System.out.println("Home Page Title Is : "+title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void homePageHeaderValue1Test() {
		String headerValue =homePage.getHeaderValue();
		Assert.assertEquals(headerValue, Constants.HOME_PAGE_HeaderValue1);
	}
	
	@Test(priority=3)
	public void homePageHeaderValue2Test() {
		String headerValue =homePage.getHeaderValue2();
		Assert.assertEquals(headerValue, Constants.HOME_PAGE_HeaderValue2);
	}
	
	@Test(priority=4)
	public void verifyHomePageHeadersListTest() {
		Assert.assertEquals(homePage.getHomePageHeadersList(), Constants.getHomePageHeadersList());		
	}
	
	@Test(priority=5)
	public void  rewardPointsLinkTest() {
		Assert.assertTrue(homePage.isRewardPointsLinkExist());
	}
	
	@Test(priority=6)
	public void verifySearchBarTest() {
		homePage.doSearch(prop.getProperty("searchfor"));
	}
}
