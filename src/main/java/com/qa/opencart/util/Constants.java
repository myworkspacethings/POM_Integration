package com.qa.opencart.util;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	public static final String LOGIN_PAGE_TITLE= "Account Login";    //String ref= "value"
	public static final String HOME_PAGE_TITLE="My Account";
	public static final String HOME_PAGE_HeaderValue1="My Account";
	public static final String HOME_PAGE_HeaderValue2="My Orders";
	public static final String REGISTER_SHEET_NAME="registration";
	public static final String ACCOUNT_SUCCESS_MESSG="account has been created";
	
	
	
	public static List<String> getHomePageHeadersList() {
		
		List<String>headersList=new ArrayList<String>();
		
		headersList.add("My Account");
		headersList.add("My Orders");
		headersList.add("My Affiliate Account");
		headersList.add("Newsletter");
		
		System.out.println("Constants Header List: "+headersList);
		
		return headersList;
	}
}
