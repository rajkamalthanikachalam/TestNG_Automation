package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.ContactsPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.utilities.TestUtil;

public class ContactsPage_TestCase extends TestBase{
	
	LoginPage loginpage;
	HomePage homePage;
	ContactsPage contactsPage;
	TestUtil testUtil;
	String sheetName = "Mention Sheet Name";
	
@BeforeMethod
public void setup(){
	
	intialization();
	testUtil = new TestUtil();	
	loginpage = new LoginPage();
	homePage = new HomePage();
	contactsPage = new ContactsPage();
	
	homePage = loginpage.Login_validatecredential(prop.getProperty("UserName"), prop.getProperty("Password"));
	testUtil.switchFrame();
	contactsPage = homePage.HomePage_ValidateContactLink();
	
}

@Test(priority = 1)
public void ContactPage_ValidateLabel(){
	Assert.assertTrue(contactsPage.ContactsPage_VerifyLabel(),"Contact Label mismatch");
	
}

@Test(priority = 2)
public void ContactPage_ValidateSelectContacts_single(){
	
	contactsPage.ContactsPage_SelectByName("Wimpy Burger");  // May fail because of locator
	
}

/*
@Test(priority = 3)
public void ContactPage_ValidateSelectContacts_Multiple(){
	
	contactsPage.ContactsPage_SelectByName("Popeye The Sailor");
	contactsPage.ContactsPage_SelectByName("Olive Oyl");
	contactsPage.ContactsPage_SelectByName("Wimpy Burger");
	
}
*/

@DataProvider
public Object[][] getCRMTestData(){
	Object data[][] = TestUtil.getTestData(sheetName);
	return data;
}

@Test(priority = 3, dataProvider = "getCRMTestData")
public void ContactsPage_ValidateNewContact(String Title, String FirstName, String LastName, String ReceiveEmail){
	homePage.HomePage_ValidateContactLink();
	contactsPage.ContactsPage_CreateNewContact(Title, FirstName, LastName, ReceiveEmail);
}


@AfterMethod
public void teardown(){
	driver.quit();
}

}
