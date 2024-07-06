package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoTest extends BaseTest{
	
	
	@BeforeClass
	public void productInfoPageSetup()
	{
		ap= lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	
	@Test
	public void proHeaderTest()
	{
		sp= ap.doSearch("macbook");
		pp=sp.selectProduct("MacBook Pro");
		String pheader= pp.getProductHeader();
		System.out.println(pheader); 
		
		//Assert.assertEquals(pp.getProductHeader(),"MackBook Pro");
	}
	
	@Test
	public void productimagecount()
	{
		sp= ap.doSearch("macbook");
		pp=sp.selectProduct("MacBook Pro");
		Assert.assertEquals(pp.imageCount(), 4);
	}
	
	@Test
	public void prodInfoTest()
	{
		sp= ap.doSearch("macbook");
		pp=sp.selectProduct("MacBook Pro");
		Map<String, String>pinfomap =pp.getPMapInfo();
		System.out.println("================ pinfo=============");
		System.out.println(pinfomap);
	}
	
	

}
