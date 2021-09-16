package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.base.BasePage;
import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class RegisterPage extends BasePage {

	ElementUtil elementUtil;
	
	private By firstname= By.id("input-firstname");
	private By lastname= By.id("input-lastname");
	private By email= By.id("input-email");
	private By telephone= By.id("input-telephone");
	private By password= By.id("input-password");
	private By confirmpassworrd= By.id("input-confirm");
	
	private By subscribeYes= By.xpath("//label[@class='radio-inline'][position()=1]");
	private By subscribeNo= By.xpath("//label[@class='radio-inline'][position()=2]");
	private By agreeCheckbox= By.xpath("//input[@name='agree']");
	private By continueButton=By.xpath("//input[@value='Continue']");
	
	private By accountSuccessMessg=By.cssSelector("#content h1");
	private By logoutLink= By.linkText("Logout");
	private By registerLink= By.linkText("Register");

	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(this.driver);		
	}
	
	public boolean accountRegistration(String firstname, String lastname, String email, String telephone, String password, String subscribe) {
		
		elementUtil.doSendKeys(this.firstname, firstname);
		elementUtil.doSendKeys(this.lastname, lastname);
		elementUtil.doSendKeys(this.email, email);
		elementUtil.doSendKeys(this.telephone, telephone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.confirmpassworrd, password);

		if (subscribe.equals("yes")) {
			elementUtil.doClick(subscribeYes);
		}else {
			elementUtil.doClick(subscribeNo);
		}

		elementUtil.doClick(agreeCheckbox);
		elementUtil.doClick(continueButton);

		String text=elementUtil.doGetText(accountSuccessMessg);
		if (text.contains(Constants.ACCOUNT_SUCCESS_MESSG)) {
			elementUtil.doClick(logoutLink);
			elementUtil.doClick(registerLink);
			return true;
		}
		return false;
	}
}
