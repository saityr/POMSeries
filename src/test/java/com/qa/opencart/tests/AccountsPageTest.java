package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constaints.Appconstants;
import com.qa.opencart.errors.AppError;

public class AccountsPageTest extends BaseTest            
{
 
@BeforeClass
public void accSetup()
{
	ap= lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
}
 
@Test
public void accPageTitleTEST()
{
	String apTitle= ap.getAccPageTitle();
	Assert.assertEquals(apTitle, Appconstants.ACCOUNTS_TITLE, AppError.TITLE_NOT_FOUND);
}


@Test
public void headersTest()
{
	List <String> hdl= ap.getHeaders();
	Assert.assertEquals(hdl, Appconstants.alist);
	
}

@DataProvider
public Object[][] searchData()
{
	return new Object[][] {   {"macbook" ,3} , {"imac"   ,1} ,   { "samsung" ,2},    {"ram"  ,0 }   };
	
	}


@Test(dataProvider= "searchData")
public void searchTest(String product, int count)
{
	sp=ap.doSearch(product);
       int spl= sp.getSearchCount();
       Assert.assertEquals(spl,count );
}
}

























