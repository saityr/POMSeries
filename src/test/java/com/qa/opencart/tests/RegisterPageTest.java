package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.errors.AppError;

public class RegisterPageTest extends BaseTest {
	
	
	@BeforeClass
	public void regSetup()
	{
		rp= lp.navigateToRegister(); 
	
	}
	
	
@Test
public void userRegister()
{
	Assert.assertTrue(rp.userRegister("siiitaa", "rama", "siiiitarama@gmail.com","987654321" , "arrtii2123", "yes"));
}


}
 