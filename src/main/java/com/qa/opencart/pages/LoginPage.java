package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constaints.Appconstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {
	private ElementUtil  eu;
	private WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver= driver;
		eu= new ElementUtil(driver);
	}		
	 
	//webelements
	
	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By loginbtn = By.xpath("//input[@value='Login']");
	private By forgotpasword = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	
	
	//actions or methods
	
	@Step("getting login page title....")
	public String getLoginPageTitle()
	{
		                     
		String title = eu.waitForTitleToBe(Appconstants.ACCOUNTS_TITLE, TimeUtil.DEFAULT_TIME);
		System.out.println("title");
		return title;
	}
	
	@Step("getting login page url......")
	public String getLoginPageUrl()
	{
		String url =eu.waitForURLContains(Appconstants.LOGIN_FRACTION_URL, TimeUtil.DEFAULT_TIME);
		System.out.println("url");
		return url;
	}
	
	public boolean checkforgotpasswordlinkExist()
	{
		return eu.doIsDisplayed(forgotpasword);
		
	}
	
	public   AccountsPage doLogin(String username, String pwd)
	
	{
		eu.doSendKeys(email,username, TimeUtil.DEFAULT_MED_TIME);
		eu.doSendKeys(password, pwd);
		eu.doClick(loginbtn);
		
		//driver.findElement(email).sendKeys(username);
		//driver.findElement(password).sendKeys(pwd);
		//driver.findElement(loginbtn).click();
		return new AccountsPage(driver);
		
		
		
		//String title= driver.getTitle();
	//	System.out.println(title);
		//return title;
		
	
	}
	public RegisterPage navigateToRegister()
	{
		
		eu.doClick(registerLink, TimeUtil.DEFAULT_TIME);
		return new RegisterPage(driver);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
