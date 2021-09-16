package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.base.BasePage;
import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class HomePage extends BasePage{

	@SuppressWarnings("unused")
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By homePageHeader=By.cssSelector("#content h2:nth-of-type(1)");
	private By homePageHeader2=By.cssSelector("#content > h2:nth-child(3)");
	private By homePageHeaderList=By.cssSelector("#content h2");
	private By rewardPointsLink=By.linkText("Reward Points");	
	private By searchBar=By.name("search");
	private By searchButton=By.className("btn-default");
	private By resultItems= By.cssSelector("#content h4 a");
	
	public HomePage(WebDriver driver) {
		this.driver=driver;	
		
		elementUtil = new ElementUtil(driver);
	}
	
	public String getHomePageTitle() {
//		return driver.getTitle();
		return elementUtil.waitForPageTitleToBePresent(5, Constants.HOME_PAGE_TITLE);
	}
	
	public String getHeaderValue() {
		if (elementUtil.doIsDisplayed(homePageHeader)) {
			return elementUtil.doGetText(homePageHeader);
		}
		return null;
	}
//		if (driver.findElement(homePageHeader).isDisplayed()) {
//			return driver.findElement(homePageHeader).getText();
//		}
//		return null;
//	}
	
	public String getHeaderValue2() {
		if (elementUtil.doIsDisplayed(homePageHeader2)) {
			return elementUtil.doGetText(homePageHeader2);
		}
		return null;
	}
//		if (driver.findElement(homePageHeader2).isDisplayed()) {
//			return driver.findElement(homePageHeader2).getText();
//		}
//		return null;
//	}
	
	public int getHomePageHeadersCount() {
//		return driver.findElements(homePageHeaderList).size();	
		return elementUtil.getElements(homePageHeaderList).size();
	}
	
	public List<String> getHomePageHeadersList() {
		
		List<String> headerList=new ArrayList<>();
		List<WebElement> list=elementUtil.getElements(homePageHeaderList);
		
		for (WebElement ele : list) {
			headerList.add(ele.getText());
		}
		System.out.println("HomePage Header List: "+headerList);
		return headerList;
	}
	
	public boolean isRewardPointsLinkExist() {
		if (elementUtil.getElements(rewardPointsLink). size()>0) {
			return true;
		}
		return false;
	}
		
	public boolean doSearch(String search){
//		driver.findElement(searchBar).sendKeys(search);
//		driver.findElement(searchButton).click();
		elementUtil.doSendKeys(searchBar, search);
		elementUtil.doClick(searchButton);
		if (elementUtil.getElements(resultItems).size()>0) {
			return true;
		}
		return false;
	}
	
	public ProductInfoPage selectProductFromList(String productName) {
		List<WebElement> resultItemList=elementUtil.getElements(resultItems);
		
		for (WebElement e : resultItemList) {
			if (e.getText().equals(productName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
}
