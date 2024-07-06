package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class SearchPage {
	
       private WebDriver driver;
       private By searchResult = By.cssSelector("div.product-thumb");
       private ElementUtil eu;
       
       public SearchPage(WebDriver driver) 
       {
    	   this.driver=driver;
    	   eu	=new ElementUtil(driver);
       }

       
      //methods
       
       public int getSearchCount()
       {
    	  List<WebElement> slist=eu.waitForVisibilityOfElemenetsLocated(searchResult, TimeUtil.DEFAULT_MED_TIME);
    	 return slist.size();
    	  
       }
       
       public ProductInfoPage selectProduct(String productName)
       {
    	   driver.findElement(By.linkText(productName)).click();
    	   
    	   
    	  // eu.doClick(By.linkText(productName), TimeUtil.DEFAULT_TIME);
    	   return new ProductInfoPage(driver);
       }
       
       
       
}
