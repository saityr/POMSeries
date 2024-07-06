package com.qa.opencart.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constaints.Appconstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic 100: Design Open Cart Application with shopping")
@Story("US 101: Design login page")

public class LoginPageTest extends BaseTest{
	
	@Description("CHECKING LOGIN PAGE")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageTitleTest()
	{
		String title1= lp.getLoginPageTitle();
		Assert.assertEquals(title1, "Account Login",AppError.TITLE_NOT_FOUND );
		
	}
	
	
	
	@Description("CHECKING  PAGE url")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageurlTest()
	{
		String url1= lp.getLoginPageUrl();
		Assert.assertTrue(url1.contains("route"));
		
	}
    
	
	
	
	
	@Test
	public void forgotlink()
	{
		boolean link= lp.checkforgotpasswordlinkExist();
		Assert.assertTrue(link);
		
	}
	@Test
	public void loginTest()
	{
		ap= lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		String aptitle= ap.getAccPageTitle();
		Assert.assertEquals(aptitle,Appconstants.ACCOUNTS_TITLE, AppError.TITLE_NOT_FOUND );
		
		
		
	 	
		
		
		
	    
	}
	
	
	
}
