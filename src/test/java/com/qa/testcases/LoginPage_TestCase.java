package com.qa.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class LoginPage_TestCase extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	
	Logger log = Logger.getLogger(LoginPage_TestCase.class);
	// Info
	//warn
	//fatal
	//error
	//debug
	
	
	public LoginPage_TestCase(){
		super();  // will call parent class constructor(Test Base) , where our property files are defined
	}

	@BeforeMethod
	public void setup(){
		intialization();
		loginPage = new LoginPage();
		log.info("Setting up browser");
	}
		
	@Test(priority = 1 )
	public void validateLoginPage_Title(){
		
		String title = loginPage.Login_getTitle();
		Assert.assertEquals(title, "#1 Free CRM for Any Business: Online Customer Relationship Software");
		log.info("Login Page Title is " + title);
		log.warn("Just for Practicing : Warn");
		log.fatal("Just for Practicing : Fatal");
		log.debug("Debug Message");
	}
	
	@Test(priority = 2)
	public void validateLoginPage_Logo(){
	
		boolean image = loginPage.Login_getimage();
		Assert.assertTrue(image);
	}
	
	@Test(priority = 3)
	public void validateLoginPage_Credential(){
		homePage = loginPage.Login_validatecredential(prop.getProperty("UserName"), prop.getProperty("Password"));
		
	}
	
	@AfterMethod
	public void teardown(){
		driver.quit();
	}
	
	
}
