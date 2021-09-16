package com.qa.opencart.test;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void productInfoSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	public void productInfoPageTitleTest() {
		homePage.doSearch("iMac");
		productInfoPage =homePage.selectProductFromList("iMac");
		
		Assert.assertEquals(productInfoPage.getProductInfoTitlePage("iMac"), "iMac");
		
	}
	
	@Test
	public void verifyProductInfoTest() {
		String productName= "macbook pro";
		homePage.doSearch(productName);
//		Assert.assertTrue(homePage.doSearch(productName));
		productInfoPage =homePage.selectProductFromList("MacBook Pro");    //iMac and is not hardcoded in you test you can manipulate
		Assert.assertTrue(productInfoPage.getProductImages()==4);
		
		Map<String, String> productInfoMap=productInfoPage.getProductinformation();
		System.out.println(productInfoMap);
		
//		{Brand=Apple, Availability=Out Of Stock, exTaxprice=$2,000.00, price=$2,000.00, 
//		name=MacBook Pro, Product Code=Product 18, Reward Points=800}

		Assert.assertEquals(productInfoMap.get("Brand"), "Apple");
		Assert.assertEquals(productInfoMap.get("Availability"), "Out Of Stock");
		Assert.assertEquals(productInfoMap.get("price"), "$2,000.0");
		Assert.assertEquals(productInfoMap.get("name"), "MacBook Pro");
		Assert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
	}
	
	@Test
	public void verifyProductInfoTest_MacBookPro() {
		String productName= "macbook pro";
		homePage.doSearch(productName);
//		Assert.assertTrue(homePage.doSearch(productName));
		productInfoPage =homePage.selectProductFromList("MacBook Pro");    //iMac and is not hardcoded in you test you can manipulate
		Assert.assertTrue(productInfoPage.getProductImages()==4);
		
		Map<String, String> productInfoMap=productInfoPage.getProductinformation();
		System.out.println(productInfoMap);
		
//		{Brand=Apple, Availability=Out Of Stock, exTaxprice=$2,000.00, price=$2,000.00, 
//		name=MacBook Pro, Product Code=Product 18, Reward Points=800}

		Assert.assertEquals(productInfoMap.get("Brand"), "Apple");
		Assert.assertEquals(productInfoMap.get("Availability"), "Out Of Stock");
		Assert.assertEquals(productInfoMap.get("price"), "2,000.0");
		Assert.assertEquals(productInfoMap.get("name"), "MacBook Pro");
		Assert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
	}
	
	@Test
	public void verifyProductInfoTest_iMac() {
		String productName= "imac";
		Assert.assertTrue(homePage.doSearch(productName));
		productInfoPage =homePage.selectProductFromList("iMac");
		Assert.assertTrue(productInfoPage.getProductImages()==3);
		
		Map<String, String> productInfoMap=productInfoPage.getProductinformation();
		System.out.println(productInfoMap);

		Assert.assertEquals(productInfoMap.get("Brand"), "Apple");
		Assert.assertEquals(productInfoMap.get("price"), "$100.00");
		Assert.assertEquals(productInfoMap.get("name"), "iMac");
		Assert.assertEquals(productInfoMap.get("Product Code"), "Product 14");
		
		productInfoPage.selectQuantity("20");
		productInfoPage.addToCart();
	}
}
