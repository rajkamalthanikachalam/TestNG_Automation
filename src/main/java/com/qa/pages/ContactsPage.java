package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.base.TestBase;

public class ContactsPage extends TestBase {

		@FindBy(xpath = "//td[contains(text(),''Contacts)]")
		WebElement contactLabel;
		
		@FindBy(xpath = "//a[contains(text(),'New Contact')]")
		WebElement newContactLink;
		
		@FindBy(id = "first_name")
		WebElement firstName;
		
		@FindBy(id = "surname")
		WebElement lastName;
		
		@FindBy(xpath = "//input[@value ='Save']")
		WebElement saveButton;
					
		public ContactsPage(){
			PageFactory.initElements(driver, this);
		}
		
		public boolean ContactsPage_VerifyLabel(){
			return contactLabel.isDisplayed();
		}
		
		public void ContactsPage_SelectByName(String name){
			driver.findElement(By.xpath("/a[text()='"+name+"']//parent::td[@class='datalistrow']"+"//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();
		}
		
		public void ContatsPage_NewContactLink(){
			
			Actions action = new Actions(driver);
			action.moveToElement(contactLabel).build().perform();    
			newContactLink.click();	
		}
		
		public void ContactsPage_CreateNewContact(String title,String FirstName, String LastName, String ReceiveEmail){
			
			Select select = new Select(driver.findElement(By.name("title")));
			select.selectByValue(title);
			
			firstName.sendKeys(FirstName);
			lastName.sendKeys(LastName);
			
			if(ReceiveEmail.equalsIgnoreCase("Yes")){
				driver.findElement(By.xpath("//input[@name='receive_email' and @value ='Y']")).click();
			}else if(ReceiveEmail.equalsIgnoreCase("No")){
				driver.findElement(By.xpath("//input[@name='receive_email' and @value ='Y']")).click();;	
			}
			saveButton.click();
			
		}
		
}
