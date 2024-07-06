package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private ElementUtil eu;
	private WebDriver driver;
	public AccountsPage(WebDriver driver)
	{
		this.driver=driver;
	eu	=new ElementUtil(driver);
	}
// BY LOCATORS
	
	
 private	By logoutlink = By.linkText("Logout");
 private    By headers= By.cssSelector("div#content h2");
 private    By search = By.name("search");
 private    By searchIcon= By.cssSelector("div#search button");
 
 
 
 //methods
 
 public String getAccPageTitle()
	{
		String title= driver.getTitle();
		System.out.println("title");
		return title;
	}
	public String getAccPageUrl()
	{
		String url= driver.getCurrentUrl();
		System.out.println("url");
		return url;
	
	}
	
	
	public boolean logoutlinkExist()
	{
		return driver.findElement(logoutlink).isDisplayed();
	}
	
	
	public List<String> getHeaders()
	{
		List<WebElement> headerlist =driver.findElements(headers);
		List<String> headerarray= new ArrayList<String>();
		for(WebElement e: headerlist)
		{
			String s1= e.getText();
			headerarray.add(s1);
		}
		return 	headerarray;
	}
	
	
	public boolean searchFieldExists()
	{
		return driver.findElement(search).isDisplayed();
	
	}
	
	public SearchPage doSearch(String skey)
	{
		System.out.println(skey);
		if(searchFieldExists())
		{
			
			
			eu.doSendKeys(search, skey);
			eu.doClick(searchIcon);
			//driver.findElement(search).sendKeys(skey);
			//driver.findElement(searchIcon).click();
			return new SearchPage(driver);
		}
		else {
			System.out.println("no page");
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
