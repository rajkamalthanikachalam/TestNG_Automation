package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.ContactsPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.utilities.TestUtil;

public class HomePage_TestCase extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
		
	
	public HomePage_TestCase(){
		
		super();
		
	}

	@BeforeMethod
	public void setup(){
		
		intialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		loginPage.Login_validatecredential(prop.getProperty("UserName"), prop.getProperty("Password"));
		homePage = new HomePage(); 
	}
	
	@Test(priority = 1)
	public void HomePage_ValidateTitle(){
		
		String Title = homePage.HomePage_VerifyTitle();
		Assert.assertEquals(Title, "CRMPRO","Home page title not matched");
		
	}
	
	@Test(priority = 2)
	public void HomePage_ValidateUser(){
	
		TestUtil.switchFrame();
		Assert.assertTrue(homePage.HomePage_ValidateUser());	
		
	}
	
	@Test(priority =3)
	public void HomePage_ValidateContactLink(){
		
		TestUtil.switchFrame();
		contactsPage = homePage.HomePage_ValidateContactLink();
		
	}
	
	@AfterMethod
	public void teardown(){
		
		driver.quit();
		
	}
}
