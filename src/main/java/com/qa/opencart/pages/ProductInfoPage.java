package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {

	@SuppressWarnings("unused")
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By productNameHeader = By.cssSelector("#content h1");
	private By productMetaData = By.cssSelector("#content .list-unstyled:nth-of-type(1) li");
	private By productPrice = By.cssSelector("#content .list-unstyled:nth-of-type(2) li");
	private By quantity = By.cssSelector("#input-quantity");
	private By addToCart = By.cssSelector("#button-cart:nth-of-type(1)");
	private By productImages = By.cssSelector(".thumbnails li img");
	private By cartItem=By.cssSelector("#product-product div.alert");
	private By viewCart=By.cssSelector("#cart-total");
	private By totalCartItems=By.cssSelector(".dropdown-menu.pull-right li:nth-of-type(2) a:nth-of-type(1)");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver= driver;
		
		elementUtil= new ElementUtil(driver);
	}
	 
	public Map<String, String> getProductinformation() {
		Map<String, String> productInfoMap=new HashMap<>();
		
		//get the product headerr name
		//name is own key bcoz there is no key
		
		productInfoMap.put("name", elementUtil.doGetText(productNameHeader));
		
		//Now for capturing information we will add data into PRODUCTINFOMAP on the basis of key and Value
		
		
		List<WebElement> productMetaDataList=elementUtil.getElements(productMetaData);
		for (WebElement e : productMetaDataList) {
			//e.getText();   But it will give you all the text as it is    //Product Code(key): Product 18(value)
			//We need to use getText with split and it will give you String[] so that's why give the position[0]&[1]..
			//split 0th value,Split 1st value
			productInfoMap.put(e.getText().split(":")[0].trim(), e.getText().split(":")[1].trim());
		}
		
		List<WebElement> productPriceList=elementUtil.getElements(productPrice);
		productInfoMap.put("price", productPriceList.get(0).getText().trim());
		productInfoMap.put("exTaxprice",productPriceList.get(1).getText().trim());
		
		return productInfoMap;  //if someone is calling productinformation it will give you productInfoMap
	}
	
	//2nd method not related to above method
	
	public void selectQuantity(String qty) {
		elementUtil.doSendKeys(quantity, qty);            //doSendKeys method will take only String otherwise you need to convert
	}
	
	public boolean addToCart() {
		elementUtil.doClick(addToCart);
		if(elementUtil.doIsDisplayed(cartItem)) {
			return true;
		}
		return false;
	}
	
	public void viewCart() {
		elementUtil.doClick(viewCart);
		elementUtil.doClick(totalCartItems);
	}

	public int getProductImages() {
		int imagesCount= elementUtil.getElements(productImages).size();
		System.out.println("Total Images: "+imagesCount);
		return imagesCount;
	}
	
	public String getProductInfoTitlePage(String productName) {
		return elementUtil.waitForPageTitleToBePresent(5, productName);
	}
}
