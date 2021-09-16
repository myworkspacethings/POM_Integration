package com.qa.opencart.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerPageSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	@DataProvider													//mapping with data provider @DataProvider will provide the data to this method
	public Object[][] getRegisterData() {
		Object data[][]=ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);             //This will get the data from excel sheet and giving you 2 dimensional array so store it in 2 dimensional array referenece
		return data;
	}
	
	@Test(dataProvider = "getRegisterData")		//mapping with @Test & @DataProvider
	public void userRegistrationTest(String firstname, String lastname, String email, 
			String telephone, String password, String subscribe) {
		registerPage.accountRegistration( firstname,  lastname,  email, telephone,  password,  subscribe);
	}
}