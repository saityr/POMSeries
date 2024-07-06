package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constaints.Appconstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class RegisterPage {
	
	
	private ElementUtil  eu;
	private WebDriver driver;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver= driver;
		eu= new ElementUtil(driver);
	}		

	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By successMessg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public boolean userRegister(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {

		eu.doSendKeys(this.firstName, firstName, TimeUtil.DEFAULT_MED_TIME);
		eu.doSendKeys(this.lastName, lastName);
		eu.doSendKeys(this.email, email);
		eu.doSendKeys(this.telephone, telephone);
		eu.doSendKeys(this.password, password);
		eu.doSendKeys(this.confirmpassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eu.doClick(subscribeYes);
		} else {
			eu.doClick(subscribeNo);
		}

		eu.doClick(agreeCheckBox);
		eu.doClick(continueButton);

		String successMesg = eu.waitForElementVisible(successMessg, TimeUtil.DEFAULT_MED_TIME).getText();

		System.out.println(successMesg);		
				
		if (successMesg.contains(Appconstants.USER_REGISTER_SUCCESS_MESSG)) {
			eu.doClick(logoutLink);
			eu.doClick(registerLink);
			return true;
		} else {
			return false;
		}

	}


	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
