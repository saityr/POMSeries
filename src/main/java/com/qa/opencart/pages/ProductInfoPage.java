package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {
	
	private ElementUtil eu;
	private WebDriver driver;
	private By productHeader = By.cssSelector("div#content h1");
	private By imagecount = By.cssSelector("div#content a.thumbnail");
	private By metaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By metaPrice= By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	private Map<String, String> pm;
	
	
	
	public ProductInfoPage(WebDriver driver)
	{
		this.driver=driver; 
	eu	=new ElementUtil(driver);
	}

	public String getProductHeader()
	{
		String header= eu.doGetText(productHeader);
		System.out.println(header);
		return header;
	}
	
	
	public int imageCount()
	{
		int imgcount  = eu.waitForVisibilityOfElemenetsLocated(imagecount, TimeUtil.DEFAULT_MED_TIME).size();
		System.out.println(imgcount);
		return imgcount;
	}
	
	
	
	public Map<String, String> getPMapInfo()
	{
		pm= new HashMap<String, String>();
		pm.put("PNAME", getProductHeader());
		pm.put("IMAGECOUNT", String.valueOf(imageCount()));
		getProdMetaData();
		getpriceData();
		return pm;
		
	}
	
		private void getProdMetaData()
		{
			
			List<WebElement> pmd= eu.getElements(metaData);
			for(WebElement e: pmd)
			{
				String metalist= e.getText();
				String mea[]= metalist.split(":");
				String metakey =mea[0];
				String metavalue= mea[1].trim();
				pm.put(metakey, metavalue);
				
			}
		}
	private void getpriceData()
	{
		
		List<WebElement> plist= eu.getElements(metaPrice);
		String proPrice=plist.get(0).getText();
		String exPrice= plist.get(1).getText().split(":")[1].trim();
		pm.put( "proPrice", proPrice);
		pm.put("exPrice", exPrice);
		
		
		
		
		
		
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
