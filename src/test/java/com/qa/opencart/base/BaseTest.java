package com.qa.opencart.base;



import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchPage;

public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
    protected LoginPage lp;
    protected Properties prop;
    protected AccountsPage ap;
    protected SearchPage sp;
    protected ProductInfoPage pp;
    protected RegisterPage rp;
    
	
	@BeforeTest
	public void setup()
	{
		df= new DriverFactory();
		prop = df.init();
		driver=df.initDriver(prop);  	
	    lp= new LoginPage(driver);
	    
		
	}

	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
	
}
