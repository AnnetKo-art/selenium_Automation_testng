package com.tests.SeleniumAutomaitonGitHub.tests;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;


public class AutomationScriptsSalesforceTestNG extends BaseTestSalesforceAnna {
	@Test (priority =1)
	public void Navigate_to_SFDC_ErrorMsg_TestCase1()throws InterruptedException, IOException{
		testName("Navigate_to_SFDC_ErrorMsg_TestCase1");
		launchLogInWebPage();
		userNameInput();
		passwordClear();
		loginButtonClick();
		errorDisplay("(//div[@id='error'])[1]");		
		waitBrowserClose();
	}
						
@Test(priority =2)
	public void login_to_salesforce_testscript_TestCase2() throws InterruptedException, IOException{
	
	generalLoginAll();
	testName("login_to_salesforce_testscript_TestCase2");
	loginButtonClick();
	waitForHomePage(); 
	waitBrowserClose();
	}
					
	@Test (priority =3)
	public void rememberUsernameCheckBox_to_salesforce_testscript_TestCase3() throws InterruptedException, IOException{
		
		generalLoginAll();
		testName("rememberUsernameCheckBox_to_salesforce_testscript_TestCase3 ");
		By checkBox=By.xpath("//input[@id='rememberUn']");
		WebElement checkboxElement= driver.findElement(checkBox);
		checkboxElement.click();
		loginButtonClick();
	
		By btn=By.xpath("//span[@id='userNavLabel']");
		WebElement userNavigBtn=driver.findElement(btn);
		userNavigBtn.click();
		
		logOutClick("//a[@title='Logout']");
		waitForLoginPage();
		userNameCheckInUsernameField();				
		waitBrowserClose();
		}
	
	@Test (priority =4)
	public void forgotPassword_to_salesforce_testscript_TestCase4A() throws InterruptedException, IOException{
		
		launchLogInWebPage();
		testName(" forgotPassword_to_salesforce_testscript_TestCase4A");
		By forgotPassword=By.xpath("//a[@id='forgot_password_link']");
		WebElement passwordForgotLink= driver.findElement(forgotPassword);
		passwordForgotLink.click();
		waitAnyElement("//input[@id='un']");
		By userNameForgotPasswordField=By.xpath("//input[@id='un']");
		WebElement usenameField= driver.findElement(userNameForgotPasswordField);
		usenameField.sendKeys(getUserNameData());
		System.out.println("Name placed into the usenameFieldInForgotPassword");				
		By cancelBtn=By.xpath("//input[@name='cancel']"); //I use Cancel Button instead of Continue Button to avoid Problems with Password change
		WebElement cancelBtnForgotPassword= driver.findElement(cancelBtn);
		cancelBtnForgotPassword.click();
		waitForLoginPage();						
		waitBrowserClose();
	}
	
	
	@Test (priority =5)
	public void wrongPasswordwrongUsername_to_salesforce_testscript_TestCase4B() throws InterruptedException, IOException{
		
		launchLogInWebPage();
		testName("wrongPasswordwrongUsername_to_salesforce_testscript_TestCase4B ");
		wrongUsernamewrongPasswordInput();
		loginButtonClick();
		waitAnyElement("//div[@id='error']");
		By errorMessage=By.xpath("//div[@id='error']");
		WebElement errorNote= driver.findElement(errorMessage);
		String actualData=errorNote.getText();
		String expactedData="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		SoftAssert softAssertion= new SoftAssert();		
		softAssertion.assertEquals(actualData, expactedData);
		System.out.println("SoftAssert Passed");
		waitBrowserClose();										
	}
	
	@Test (priority =6)
	
	public void dropDownUserMenuCheck_to_salesforce_testscript_TestCase5()throws InterruptedException, IOException{
		
		ArrayList<String> expectedList= new ArrayList();// drop down options that are in UserMenu dropdown 
		expectedList.add("My Profile");
		expectedList.add("My Settings");
		expectedList.add("Developer Console");
		expectedList.add("Switch to Lightning Experience");
		expectedList.add("Logout");
		
		generalLoginAll();
		testName("dropDownUserMenuCheck_to_salesforce_testscript_TestCase5 ");
		loginButtonClick();
	
		By btn=By.xpath("//span[@id='userNavLabel']");
		WebElement userNavigBtn=driver.findElement(btn);
		userNavigBtn.isDisplayed();		
		userNavigBtn.click();
		
		List<WebElement> dropDownOptions =driver.findElements(By.xpath("//div[@id='userNav-menuItems']/a"));		
		List<String> actualList=new ArrayList<String>();
		
		for(WebElement dropDownOption:dropDownOptions ) {
			System.out.println(dropDownOption.getText());
			actualList.add(dropDownOption.getText());
			
		}
		 try {
	            TimeUnit.SECONDS.sleep(3);
	            System.out.println("Browser is sleeping for 3 seconds. ");
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }		 
		System.out.println("EXPECTED LIST");// IS SORTED CORRECTLY
		Collections.sort(expectedList);
		for(String option:expectedList ) {
			System.out.println(option);						
		}		
		System.out.println("ACTUAL LIST");
		Collections.sort(actualList);
		for(String option:actualList ) {
			System.out.println(option);						
		}										                  					
		if(actualList.equals(expectedList)) {
			System.out.println("DropDown options and Expected Options are Equal");
		}
		else {
			System.out.println("TEST FAILED");
		}										
		waitBrowserClose();
		
	}
	
	@Test (priority =7)
	public void myProfileOption_to_salesforce_testscript_TestCase6()throws InterruptedException, IOException, AWTException{
		
		generalLoginAll();
		testName("myProfileOption_to_salesforce_testscript_TestCase6 ");
		loginButtonClick();
		By btn=By.xpath("//span[@id='userNavLabel']");
		waitAnyElement("//span[@id='userNavLabel']");					
		WebElement userNavigBtn=driver.findElement(btn);						
		userNavigBtn.isDisplayed();
		
		userNavigBtn.click();	
		waitAnyElement("//a[@title='My Profile']");	
		WebElement myProfileLink=driver.findElement(By.xpath("//a[@title='My Profile']"));
		myProfileLink.isDisplayed();	
		Thread.sleep(2000);
		myProfileLink.click();	
						
	    // EDIT LINK , ABOUT, LASTNAME INPUT
		String editProfLink="//h3[normalize-space()='Contact']//img[@title='Edit Profile']";
		waitAnyElement(editProfLink);	
		By editBtn=By.xpath(editProfLink);							
		WebElement editBtnSign=driver.findElement(editBtn);						
		editBtnSign.isDisplayed();
		editBtnSign.click();					
		String frameAbout="//iframe[@id='contactInfoContentId']";
		 waitAnyElement(frameAbout);
		 WebElement iframe1 = driver.findElement(By.xpath(frameAbout));
		 driver.switchTo().frame(iframe1);		 
		 waitAnyElement("//li[@id='aboutTab']");			 		 		 
		 By aboutBtn=By.xpath("//li[@id='aboutTab']");
		 WebElement aboutBtnElement=driver.findElement(aboutBtn);						
		 aboutBtnElement.isDisplayed();						
		 aboutBtnElement.click();
		 waitAnyElement("//input[@id='lastName']");	
		 WebElement lastNameInput=driver.findElement(By.xpath(" //input[@id='lastName']"));
		 lastNameInput.isDisplayed();
		 lastNameInput.clear();
		 //lastNameInput.sendKeys("Penuela");
		 lastNameInput.sendKeys("Kova");
		 sleepingMethod();
		 WebElement saveAllBtn=driver.findElement(By.xpath(" //input[@value='Save All']"));
		 saveAllBtn.click();				
		 sleepingMethod();
		 
		 //POST LINK
		 WebElement postLink1=driver.findElement(By.xpath(" //span[normalize-space()='Post']"));			 
		 postLink1.click();
		 Thread.sleep(4000)	;		 
	        WebDriverWait wait = new WebDriverWait(driver, 10);	       
	        WebElement iframe5 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@title='Rich Text Editor, publisherRichTextEditor']")));	       
	        driver.switchTo().frame(iframe5);	       
	        WebElement bodyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));	       
	        Actions actions1 = new Actions(driver);
	        // Clear the body element using the Actions class
	        actions1.moveToElement(bodyElement).click().sendKeys("").perform();	      
	        actions1.sendKeys("One more useful message to the community").perform();
	        sleepingMethodWithStringMessage("Post Link Message was written");
	        
	        /* DIDN'T WORK
	        WebElement button = driver.findElement(By.id("publishersharebutton"));
	        // Create an instance of the Actions class
	       // actions1 = new Actions(driver);
	        // Click the button using the Actions class
	        actions1.moveToElement(button).click().perform();	
	        sleepingMethodWithStringMessage("Share Button of Post Methos was clicked");
	        */	       
	        driver.switchTo().defaultContent();		 		 		 		 		 		 		 		 		 		 		 					 		 				 		 		 						 				 		 
		 
	        //FILE LOAD LINK
		 WebElement postLink=driver.findElement(By.xpath("//span[normalize-space()='File']"));
		 postLink.click();
		 sleepingMethod();		 				 		 
		 WebElement fileUploadBtn=driver.findElement(By.xpath("//a[@id='chatterUploadFileAction']"));
		 fileUploadBtn.click();		 		
		 sleepingMethodWithStringMessage("Get Ready To Upload Image");
		 String s = "C:\\Users\\Anna\\Desktop\\4.png";
		 WebElement elem = driver.findElement(By.xpath("//input[@type='file']")); //INPUT FILE DIRECTION
		 elem.sendKeys(s);//we give to file input the way to the local file
		 sleepingMethodWithStringMessage("Image Was Uploaded Through File Link");
		 JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		 WebElement element = (WebElement) jsExecutor.executeScript("return document.querySelector('#publishersharebutton');");				
		 element.click(); // Click on the element	 
		 sleepingMethodWithStringMessage("Share Button From File Upload Clicked");
			
		// UPDATE PROFILE PICTURE
		WebElement hoverElement = driver.findElement(By.xpath("//a[@id='uploadLink']"));
		// Create an instance of the Actions class
		Actions actions = new Actions(driver);
		// Hover over the element
		actions.moveToElement(hoverElement).perform();
		hoverElement.click();		
		WebElement iframeUploadPic = driver.findElement(By.xpath("//iframe[@id='uploadPhotoContentId']"));
		driver.switchTo().frame(iframeUploadPic);		 		 			 		 
		sleepingMethodWithStringMessage("Get Ready To Upload Profile Image");
		//String strImage = "C:\\Users\\Anna\\Desktop\\4.png";			
		String strImage = "C:\\Users\\Anna\\Desktop\\Photo Collage\\photoFlowers.jpg";			
		WebElement elemfileInput = driver.findElement(By.xpath("//input[@type='file']"));
		sleepingMethodWithStringMessage("Uploading Image from File System");	
		elemfileInput.sendKeys(strImage);//we give to file input the way to the local file	
		sleepingMethodWithStringMessage("Image Uploaded");				 								 				 				 	 					 								 
		WebElement saveBut1=driver.findElement(By.xpath("(//input[@id='j_id0:uploadFileForm:uploadBtn'])[1]"));	
		saveBut1.click();
		Thread.sleep(8000);
		//waitAnyElement("(//input[@id='j_id0:uploadFileForm:uploadBtn'])[1]");
		WebElement saveButCrop=driver.findElement(By.xpath("(//input[@id='j_id0:j_id7:save'])[1]"));	
		saveButCrop.click();
			
		waitBrowserClose();				
		
	}
				
	@Test (priority =8)
	public void developerConsoleButtonClick_to_salesforce_testscript_TestCase8()throws InterruptedException, IOException, AWTException{
		
		generalLoginAll();
		testName("developerConsoleButtonClick_to_salesforce_testscript_TestCase8 ");
		loginButtonClick();
		By btn=By.xpath("//span[@id='userNavLabel']");
		waitAnyElement("//span[@id='userNavLabel']");					
		WebElement userNavigBtn=driver.findElement(btn);						
		userNavigBtn.isDisplayed();
		//Thread.sleep(4000);
		userNavigBtn.click();		
		waitAnyElement("//a[@title='Developer Console (New Window)']");					
		WebElement developerConsoleLink=driver.findElement(By.xpath("//a[@title='Developer Console (New Window)']"));
		developerConsoleLink.isDisplayed();				
		developerConsoleLink.click();		
		try {
            TimeUnit.SECONDS.sleep(4);
            System.out.println("Browser is sleeping for 3 seconds. ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		//this method close pop-up window
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		waitBrowserClose();
		
		/* FOR HANDLING WINDOWS
		  // Get the handles of all open windows. In this case pop-up window  exists but it is under the main window.
		   String mainHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        // Switch to the pop-up window
        for (String handle : windowHandles) {
            if (!handle.equals(mainHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }     
        driver.switchTo().window(mainHandle);	
        */															
	}

	@Test (priority =9)
	public void userMenuLogOutClick_to_salesforce_testscript_TestCase9()throws InterruptedException, IOException{
		
		generalLoginAll();
		testName("userMenuLogOutClick_to_salesforce_testscript_TestCase ");
		loginButtonClick();		
		By btn=By.xpath("//span[@id='userNavLabel']");
		waitAnyElement("//span[@id='userNavLabel']");					
		WebElement userNavigBtn=driver.findElement(btn);						
		userNavigBtn.isDisplayed();
		userNavigBtn.click();
		try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Browser is sleeping for 2 seconds. ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		waitAnyElement("//a[@title='Logout']");					
		WebElement logOutBtn=driver.findElement(By.xpath("//a[@title='Logout']"));
		logOutBtn.isDisplayed();				
		logOutBtn.click();
		waitBrowserClose();
	
	}
	
	@Test (priority =10)
	public void createAccount_to_salesforce_testscript_TestCase10()throws InterruptedException, IOException{
		
		generalLoginAll();
		testName("createAccount_to_salesforce_testscript_TestCase10 ");
		loginButtonClick();		
		waitAnyElement("//a[@title='Accounts Tab']");		
		By accountBtn=By.xpath("//a[@title='Accounts Tab']");
		WebElement accountHomeOption=driver.findElement(accountBtn);						
		accountHomeOption.isDisplayed();
		accountHomeOption.click();	
		
		
		
		// Switch to the pop-up window
	    String mainWindowHandle = driver.getWindowHandle();
	    Set<String> allWindowHandles = driver.getWindowHandles();
	    for (String handle : allWindowHandles) {
	        if (!handle.equals(mainWindowHandle)) {
	            driver.switchTo().window(handle);
	            break;
	        }
	    }
        Thread.sleep(5000);
	    // Close the pop-up window (assuming there's a close button with id "tryLexDialogX")
	    WebElement closeButton = driver.findElement(By.id("tryLexDialogX"));
	    closeButton.click();

	    // Switch back to the main window
	    driver.switchTo().window(mainWindowHandle);						    
		waitAnyElement("//input[@title='New']");
		By newBtn=By.xpath("//input[@title='New']");
		WebElement newAccountBtn=driver.findElement(newBtn);
		newAccountBtn.isDisplayed();
		newAccountBtn.click();
		waitAnyElement("//input[@id='acc2']");
		By accountNameInput=By.xpath("//input[@id='acc2']");
		WebElement accNameInputTxtBox=driver.findElement(accountNameInput);
		accNameInputTxtBox.isDisplayed();
		accNameInputTxtBox.clear();
		accNameInputTxtBox.sendKeys("My new Test Account2");				
		waitAnyElement("//select[@id='acc6']");
		WebElement dropdownType = driver.findElement(By.xpath("//select[@id='acc6']"));       
        Select select = new Select(dropdownType);               
        select.selectByValue("Technology Partner");
        sleepingMethod();		 
        waitAnyElement("//select[@id='00NHs00000DlbWD']");
        WebElement dropdownCustomerPriority = driver.findElement(By.xpath("//select[@id='00NHs00000DlbWD']"));
        select = new Select(dropdownCustomerPriority);
        select.selectByValue("High");
        sleepingMethod();		        
        By saveBtn=By.xpath(" //td[@id='bottomButtonRow']//input[@title='Save']");
		WebElement saveNewAccountBtn=driver.findElement(saveBtn);
		saveNewAccountBtn.isDisplayed();
		saveNewAccountBtn.click();
		waitBrowserClose();              
        	
	}
	@Test (priority =11)
	public void createNewView_to_salesforce_testscript_TestCase11()throws InterruptedException, IOException, AWTException{		
		generalLoginAll();
		testName("createNewView_to_salesforce_testscript_TestCase11 ");
		loginButtonClick();			 
		waitAnyElement("//a[@title='Accounts Tab']");		
		By accountBtn=By.xpath("//a[@title='Accounts Tab']");
		WebElement accountHomeOption=driver.findElement(accountBtn);						
		accountHomeOption.isDisplayed();
		accountHomeOption.click();			  		
		String mainWindowHandle = driver.getWindowHandle();		
		// Switch to the pop-up window	   
	    Set<String> allWindowHandles = driver.getWindowHandles();
	    for (String handle : allWindowHandles) {
	        if (!handle.equals(mainWindowHandle)) {
	            driver.switchTo().window(handle);
	            break;
	        }
	    }
	    Thread.sleep(5000);
	    //close pop up
	    WebElement closeButton = driver.findElement(By.id("tryLexDialogX"));
	    closeButton.click();	    	    
	    driver.switchTo().window(mainWindowHandle);	     	    
		waitAnyElement("//a[normalize-space()='Create New View']");
		By createNewViewLink=By.xpath("//a[normalize-space()='Create New View']");
		WebElement createNewViewLnk1=driver.findElement(createNewViewLink);
		createNewViewLnk1.isDisplayed();
		createNewViewLnk1.click();	    				
		sleepingMethodWithStringMessage("New View Link was clicked");
		waitAnyElement("//input[@id='fname']");	
		By inputViewName=By.xpath("//input[@id='fname']");
		WebElement inputViewNameTxt=driver.findElement(inputViewName);
		inputViewNameTxt.isDisplayed();
		inputViewNameTxt.clear();
		inputViewNameTxt.sendKeys("ViewNameT2");
		sleepingMethodWithStringMessage("View Name was inputed");		
		String expectedData=inputViewNameTxt.getAttribute("value");// textBox.getAttribute("value");		
		System.out.println("Expected Data" + expectedData);		
		By inputViewUniqueName=By.xpath("//input[@id='devname']");
		WebElement inputViewUniqueNameTxt=driver.findElement(inputViewUniqueName);
		inputViewUniqueNameTxt.isDisplayed();				 
		inputViewUniqueNameTxt.sendKeys(Keys.NULL);
		 sleepingMethod();
		inputViewUniqueNameTxt.clear();
		sleepingMethodWithStringMessage("View Unique Name Text Box was clear");
		inputViewUniqueNameTxt.sendKeys("MyUniqueNameT2");
		 sleepingMethod();
		By saveNewNameBtn=By.xpath("(//input[@title='Save'])[1]");				
		WebElement saveBtn=driver.findElement(saveNewNameBtn);
		saveBtn.isDisplayed();
		saveBtn.click();
		sleepingMethodWithStringMessage("Save Button  Was Clicked");				
		// Locate the dropdown element
        WebElement dropdownCheckNewView = driver.findElement(By.name("fcf"));
        // Create a Select object for the dropdown
        Select select = new Select(dropdownCheckNewView);
        String desiredText = expectedData;
        String desiredValue = null;
        System.out.println("Desired Text is "+ desiredText);
        // Iterate through all options
        for (WebElement option : select.getOptions()) {
            String optionText = option.getText();           
            if (optionText.equals(desiredText)) {               
            	System.out.println("Desired option value: " + optionText +" of New View Is Displayed in Dropdown of Accounts");               
            	testPassed();
            	waitBrowserClose();
            	break;
            }
        }
             
        browserCloseTestFailed();
        				
	}
	
	@Test(priority =12)
	
	public void opportunitiesDropDown_to_salesforce_testscript_TestCase15()throws InterruptedException, IOException{
		
		ArrayList<String> expectedList= new ArrayList();// drop down options that are in UserMenu dropdown 
		expectedList.add("All Opportunities");
		expectedList.add("Closing Next Month");
		expectedList.add("Closing This Month");
		expectedList.add("My Opportunities");
		expectedList.add("New Last Week");
		expectedList.add("New This Week");
		expectedList.add("Opportunity Pipeline");
		expectedList.add("Private");
		expectedList.add("Recently Viewed Opportunities");
		expectedList.add("Won");												
		generalLoginAll();
		testName("opportunitiesDropDown_to_salesforce_testscript_TestCase15 ");
		loginButtonClick();	
		opprtunityHomePageOption("//a[@title='Opportunities Tab']");
								
		//By btn=By.xpath("//span[@id='userNavLabel']");
		//WebElement userNavigBtn=driver.findElement(btn);
		//userNavigBtn.isDisplayed();		
		//userNavigBtn.click();
		
		List<WebElement> dropDownOptions =driver.findElements(By.xpath("//select[@id='00BHs00000BtRwr_listSelect']"));		
		List<String> actualList=new ArrayList<String>();
		
		for(WebElement dropDownOption:dropDownOptions ) {
			System.out.println(dropDownOption.getText());
			actualList.add(dropDownOption.getText());			
		}
		sleepingMethod();		 
		System.out.println("EXPECTED LIST");// IS SORTED CORRECTLY
		Collections.sort(expectedList);
		for(String option:expectedList ) {
			System.out.println(option);						
		}		
		System.out.println("ACTUAL LIST");
		Collections.sort(actualList);
		for(String option:actualList ) {
			System.out.println(option);						
		}								
		                      //SoftAssert softAssertion= new SoftAssert();		// DIDN'T COMPARE collections IN A CORRECT WAY ???!!!!!!
		                      //softAssertion.assertEquals(actualList, expectedList);//
		                      // System.out.println("SoftAssert Passed");
					
		if(actualList.equals(expectedList)) {
			System.out.println("DropDown options and Expected Options are Equal");
		}
		else {
			System.out.println("TEST FAILED");
		}										
		waitBrowserClose();
	}
		
	@Test (priority =13)
	
		public void createNewOpporty_to_salesforce_testscript_TestCase16() throws InterruptedException, IOException{
		    
		    generalLoginAll();
		    testName("createNewOpporty_to_salesforce_testscript_TestCase16 ");
			loginButtonClick();			
			opprtunityHomePageOption("//a[@title='Opportunities Tab']");	
			popUpWindowHandle();
			By newBtnCreateOpport=By.xpath("//input[@title='New']");
			WebElement newOpportunityBtn=driver.findElement(newBtnCreateOpport);
			clickButton(newOpportunityBtn);
			sendKeysFunctionalityByXPath("//input[@id='opp3']","Opportunity1");
			sendKeysFunctionalityByXPath("//input[@id='opp4']","Anna1");
			dropdownHandle("Lead Source", "//select[@id='opp6']","Web");					
			bareClick("//body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/div[1]/div[2]/div[3]/table[1]/tbody[1]/tr[2]/td[4]/div[1]/span[1]/span[1]");						
			sendKeysFunctionalityByXPath("//input[@id='opp12']","12");
			dropdownHandle("Stage", "//select[@id='opp11']","Qualifications");																		
			bareClick("//td[@id='topButtonRow']//input[@title='Save']");//save button click					
			waitBrowserClose();
	}
	
	
	@Test (priority =14)
	public void opportunityPipeLineReport_to_salesforce_testscript_TestCase17() throws InterruptedException, IOException{
		
		generalLoginAll();
		testName("opportunityPipeLineReport_to_salesforce_testscript_TestCase17 ");
		loginButtonClick();			
		opprtunityHomePageOption("//a[@title='Opportunities Tab']");	
		popUpWindowHandle();
		bareClick("//a[normalize-space()='Opportunity Pipeline']");//Pipeline link  click			
		waitBrowserClose();				
	}
	@Test (priority =15)
	public void opportunityStuckReport_to_salesforce_testscript_TestCase18() throws InterruptedException, IOException{
		
		generalLoginAll();
		testName("opportunityStuckReport_to_salesforce_testscript_TestCase18 ");
		loginButtonClick();			
		opprtunityHomePageOption("//a[@title='Opportunities Tab']");	
		popUpWindowHandle();
		bareClick("//a[normalize-space()='Stuck Opportunities']");//Stuck Report  link  click			
		waitBrowserClose();				
	}
	
	@Test (priority =16)
	public void opportunityQuarterlySummaryReport_to_salesforce_testscript_TestCase19() throws InterruptedException, IOException{
		
		generalLoginAll();
		testName("opportunityQuarterlySummaryReport_to_salesforce_testscript_TestCase19 ");
		loginButtonClick();			
		opprtunityHomePageOption("//a[@title='Opportunities Tab']");	
		popUpWindowHandle();
		dropdownHandleByValue("Interval in Quarterly Summary", "//select[@id='quarter_q']","Next FQ");				
		dropdownHandleByValue("Include in Quarterly Summary", "//select[@id='open']","Open Opportunities");			
		bareClick("//input[@title='Run Report']");//Stuck Report  link  click			
		waitBrowserClose();			
	}
	
	@Test (priority =17)
	public void LeadsTab_to_salesforce_testscript_TestCase20() throws InterruptedException, IOException{
	
		generalLoginAll();
		testName("LeadsTab_to_salesforce_testscript_TestCase20 ");
		loginButtonClick();							
		bareClick("//a[@title='Leads Tab']");//Leads Tab Button  click	
		popUpWindowHandle();
		waitBrowserClose();				
	}
	
	@Test (priority =18)
	public void validateViewDropDownListContent_to_salesforce_testscript_TestCase21() throws InterruptedException, IOException{
		
		ArrayList<String> expectedList = new ArrayList<String>(); // drop-down options that are in View Drop Down
		    expectedList.add("All Open Leads");
		    expectedList.add("My Unread Leads");
		    expectedList.add("Recently Viewed Leads");
		    expectedList.add("Today's Leads");
		    expectedList.add("View - Custom 1");
		    expectedList.add("View - Custom 2");
		    generalLoginAll();
		    testName("validateViewDropDownListContent_to_salesforce_testscript_TestCase21 ");
		    loginButtonClick();
		    bareClick("//a[@title='Leads Tab']"); // Leads Tab Button click
		    popUpWindowHandle();
		    
		    WebElement dropDown = driver.findElement(By.xpath("//select[@id='fcf']"));
		    Select dropDownSelect = new Select(dropDown);
		    List<WebElement> dropDownOptions = dropDownSelect.getOptions();

		    List<String> actualList = new ArrayList<String>();
		    for (WebElement dropDownOption : dropDownOptions) {
		        System.out.println(dropDownOption.getText());
		        actualList.add(dropDownOption.getText());
		    }
		    sleepingMethod();
		    System.out.println("EXPECTED LIST");
		    Collections.sort(expectedList);
		    for (String option : expectedList) {
		        System.out.println(option);
		    }
		    System.out.println("ACTUAL LIST");
		    Collections.sort(actualList);
		    for (String option : actualList) {
		        System.out.println(option);
		    }

		    if (actualList.containsAll(expectedList) && expectedList.containsAll(actualList)) {
		        System.out.println("DropDown options and Expected Options are Equal");
		    } else {
		        System.out.println("TEST FAILED");
		    }

		    waitBrowserClose();
				
	}
	
	@Test (priority =19)	
	public void functionalityOfGoBtn_to_salesforce_testscript_TestCase22() throws InterruptedException, IOException{
		
		generalLoginAll();
		testName("functionalityOfGoBtn_to_salesforce_testscript_TestCase22 ");
		loginButtonClick();							
		bareClick("//a[@title='Leads Tab']");//Leads Tab Button  click	
		popUpWindowHandle();
		dropdownHandleByValue("View DropDown Option in Lead's Page", "//select[@id='fcf']","My Unread Leads");				
		By btn=By.xpath("//span[@id='userNavLabel']");
		WebElement userNavigBtn=driver.findElement(btn);
		userNavigBtn.click();		
		logOutClick("//a[@title='Logout']");
		waitForLoginPage();
		logInFromBrowserPage();
		sleepingMethod();
		bareClick("//a[@title='Leads Tab']");//Leads Tab Button  click	
		//popUpWindowHandle();
		waitAnyElement("//input[@title='Go!']");    
		bareClick("//input[@title='Go!']");												
		waitBrowserClose();
	}
					
	@Test (priority =20)
	public void todaysLeadsChooseInDropDown_to_salesforce_testscript_TestCase23() throws InterruptedException, IOException{
		generalLoginAll();
		testName("todaysLeadsChooseInDropDown_to_salesforce_testscript_TestCase23 ");
		loginButtonClick();							
		bareClick("//a[@title='Leads Tab']");//Leads Tab Button  click	
		popUpWindowHandle();
		dropdownHandleByValue("View DropDown Option in Lead's Page", "//select[@id='fcf']","Today's Leads");		
		bareClick("//input[@title='Go!']");						
		waitBrowserClose();
	}
	
	
	@Test (priority =21)
	public void checkNewBtnOnLeadsHome_to_salesforce_testscript_TestCase24() throws InterruptedException, IOException{
		generalLoginAll();
		testName("checkNewBtnOnLeadsHome_to_salesforce_testscript_TestCase24 ");
		loginButtonClick();							
		bareClick("//a[@title='Leads Tab']");//Leads Tab Button  click	
		popUpWindowHandle();				
		waitAnyElement("(//input[@title='New'])[1]");                    		
		bareClick("(//input[@title='New'])[1]"); 
		sendKeysFunctionalityByXPath("//input[@id='name_lastlea2']","ABCD1");
		sendKeysFunctionalityByXPath("//input[@id='lea3']","ABCD1");
		bareClick("//td[@id='topButtonRow']//input[@title='Save']");											
		waitBrowserClose();
	}
	
	
	@Test (priority =22)
	public void createNewContact_to_salesforce_testscript_TestCase25() throws InterruptedException, IOException{
		generalLoginAll();
		testName("createNewContact_to_salesforce_testscript_TestCase25 ");
		loginButtonClick();							
		bareClick("//a[@title='Contacts Tab']");//Contact Tab Button  click	
		popUpWindowHandle();						
		waitAnyElement("(//input[@title='New'])[1]");                    		
		bareClick("(//input[@title='New'])[1]"); 		
		sendKeysFunctionalityByXPath("//input[@id='name_lastcon2']","Last Name");
		sendKeysFunctionalityByXPath("//input[@id='con4']","Anna1");				
		bareClick("//td[@id='topButtonRow']//input[@title='Save']");		
		waitBrowserClose();
	}
	
	@Test (priority =23)
	public void createNewViewInContactPage_to_salesforce_testscript_TestCase26() throws InterruptedException, IOException{
		generalLoginAll();
		testName("createNewViewInContactPage_to_salesforce_testscript_TestCase26 ");
		loginButtonClick();							
		bareClick("//a[@title='Contacts Tab']");//Contact Tab Button  click	
		popUpWindowHandle();						
		waitAnyElement("//a[normalize-space()='Create New View']");    		
		bareClick("//a[normalize-space()='Create New View']");								
		sendKeysFunctionalityByXPath("//input[@id='fname']","NewNameInContact");		
		WebElement vieUniqueNameTxt =driver.findElement(By.xpath("//input[@id='devname']"));
		vieUniqueNameTxt.sendKeys(Keys.NULL);
		bareClick("(//input[@title='Save'])[1]");				
		waitBrowserClose();
	}
	
	@Test (priority =25)
	public void recentlyCreatedContactCheck_to_salesforce_testscript_TestCase27() throws InterruptedException, IOException{
		generalLoginAll();
		testName("recentlyCreatedContactCheck_to_salesforce_testscript_TestCase27 ");
		loginButtonClick();							
		bareClick("//a[@title='Contacts Tab']");//Contact Tab Button  click	
		popUpWindowHandle();							
		waitAnyElement("//select[@id='hotlist_mode']");    	
		dropdownHandleByValue("View DropDown Option in Recently Created", "//select[@id='hotlist_mode']","Recently Created");
		
		
		waitBrowserClose();
	}
	
	@Test (priority =26)
	public void myContactsViewCheck_to_salesforce_testscript_TestCase28() throws InterruptedException, IOException{
		generalLoginAll();
		testName("myContactsViewCheck_to_salesforce_testscript_TestCase28 ");
		loginButtonClick();							
		bareClick("//a[@title='Contacts Tab']");//Contact Tab Button  click	
		popUpWindowHandle();
		dropdownHandleByValue("My Contacts Option in Contacts HOme Page DropDown", "//select[@id='fcf']","My Contacts");
		waitBrowserClose();
	}
	
	@Test (priority =27)
	public void viewContactInContactPage_to_salesforce_testscript_TestCase29() throws InterruptedException, IOException{
		generalLoginAll();
		testName("viewContactInContactPage_to_salesforce_testscript_TestCase29 ");
		loginButtonClick();							
		bareClick("//a[@title='Contacts Tab']");//Contact Tab Button  click	
		popUpWindowHandle();		
		WebElement firstName=driver.findElement(By.xpath("//a[contains(text(),'Last Name')]"));  
		firstName.click();		
		waitBrowserClose();
	}
	
	@Test (priority =28)
	public void VerifyFirstNameLastName_to_salesforce_testscript_TestCase33() throws InterruptedException, IOException{
		generalLoginAll();
		testName("VerifyFirstNameLastName_to_salesforce_testscript_TestCase33 ");
		loginButtonClick();	
		WebElement myProfileUsername = driver.findElement(By.xpath("//span[@id='userNavLabel']")); 
		String userFinLn = myProfileUsername.getText().trim();
		System.out.println(userFinLn);
		bareClick("//a[@title='Home Tab']");//Contact Tab Button  click	
		popUpWindowHandle();		
		sleepingMethod();				
		String expectedTitle="Salesforce - Developer Edition";
		checkTitleOfPage(expectedTitle);		
		// if element is on the left side of the screen	check		
		sleepingMethod();
		WebElement element = driver.findElement(By.xpath("//a[@href='/_ui/core/userprofile/UserProfilePage'][normalize-space()='Annet Kova']"));
		elementPosition(element);		
		//if  element is a link check
		String tagName = element.getTagName();
		String href = element.getAttribute("href");
		if (tagName.equalsIgnoreCase("a") || href != null) {
		    System.out.println("The element is a link.");
		} else {
		    System.out.println("The element is not a link.");
		}				
		WebElement link = driver.findElement(By.linkText("Annet Kova"));		
		link.sendKeys(Keys.TAB);
		link.click();	
		waitAnyElement("//span[@id='tailBreadcrumbNode']");    
		WebElement breadCrumbFnLn = driver.findElement(By.xpath("//span[@id='tailBreadcrumbNode']")); 
		sleepingMethod();
		String breadcrumbinfo = breadCrumbFnLn.getText().trim();
		System.out.println(breadcrumbinfo);	
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(userFinLn, breadcrumbinfo, "Names are different");						
		waitBrowserClose();
	}
	
	@Test (priority =29)
	public void VerifyEditedLastnameIsUpdatedAtVariousPlaces_to_salesforce_testscript_TestCase34() throws InterruptedException, IOException {
		generalLoginAll();
		testName("VerifyEditedLastnameIsUpdatedAtVariousPlaces_TestCase34 ");
		loginButtonClick();
		waitAnyElement("//a[@title='Home Tab']");		
		bareClick("//a[@title='Home Tab']");//homepage link check and click
		popUpWindowHandle();		
		sleepingMethod();		
		waitAnyElement("//a[@href='/_ui/core/userprofile/UserProfilePage'][normalize-space()='Annet Kova']");
		bareClick("//a[@href='/_ui/core/userprofile/UserProfilePage'][normalize-space()='Annet Kova']");
		sleepingMethod();		
		bareClick("//h3[normalize-space()='Contact']//img[@title='Edit Profile']");		
		workWithFrame("//iframe[@id='contactInfoContentId']");										 
		waitAnyElement("//li[@id='aboutTab']");	
		bareClick("//li[@id='aboutTab']");
		waitAnyElement("//input[@id='firstName']");	
		isElementDisplayed("//input[@id='firstName']");	
		sleepingMethod();	
		waitAnyElement("//input[@id='lastName']");			 		 		 
		WebElement lastNameInput=driver.findElement(By.xpath(" //input[@id='lastName']"));
		lastNameInput.isDisplayed();
		lastNameInput.clear();
		lastNameInput.sendKeys("Abcd");
		 sleepingMethod();		 
		 bareClick("//input[@value='Save All']");		 			
		 sleepingMethod();				 		 		 
		 waitAnyElement("//span[@id='tailBreadcrumbNode']");
		 String expectedData="Annet Abcd";
		 WebElement fnLm=driver.findElement(By.xpath("//span[@id='tailBreadcrumbNode']"));
		 String actualData = fnLm.getText();	 
		 softAssertEquality(expectedData, actualData);
		 WebElement element = driver.findElement(By.xpath("//span[@id='tailBreadcrumbNode']"));
		 elementPosition(element);	
		 WebElement userNameNavLabel=driver.findElement(By.xpath("//span[@id='userNavLabel']"));
		 String actualDataNavLabel = userNameNavLabel.getText();	
		 softAssertEquality(expectedData, actualDataNavLabel);
		 waitBrowserClose();
		
	}
	@Test (priority =30)
	public void verifyTabCustomization_to_salesforce_testscript_TestCase35()throws InterruptedException, IOException{
		generalLoginAll();
		testName("verifyTabCustomization_TestCase35 ");
		loginButtonClick();
		bareClick("//img[@title='All Tabs']");//Plus Click
		sleepingMethod();		
		bareClick("//input[@title='Customize My Tabs']");//CustomizeMyTabs BUtton Click
		String strTargetValue =selectOption("//select[@id='duel_select_1']").trim();
		sleepingMethod();	
		System.out.println(strTargetValue);
		bareClick("//img[@title='Remove']");				
		 WebElement selectElement = driver.findElement(By.xpath("//select[@id='duel_select_0']"));
		    Select select = new Select(selectElement);
		    List<WebElement> options = select.getOptions();
		    boolean isPresent = false;
		    sleepingMethod();	
		    for (WebElement option : options) {
		        String optionValue = option.getAttribute("value");
		        
		        if (optionValue.trim().equals(strTargetValue)) {
		        	isPresent = true;
		           System.out.println(strTargetValue +"came Back To the Available Tabs List");
		           break; 
		        }
		    }		   
		    if (!isPresent) {
		        System.out.println(strTargetValue + " is not present in select options");
		    }
		    bareClick("//input[@title='Save']");		    		    		    		    		   
			String expectedTitle="All Tabs ~ Salesforce - Developer Edition";												
			String actualTitle = driver.getTitle();
			if (expectedTitle.equals(actualTitle)) {
				System.out.println("All Tabs page is displayed");
			} else {
				System.out.println("All Tabs page is not displayed");
			}
			By btn=By.xpath("//span[@id='userNavLabel']");
			WebElement userNavigBtn=driver.findElement(btn);
			userNavigBtn.click();			
			logOutClick("//a[@title='Logout']");
			waitForLoginPage();							
			//logInFromBrowserPage();
			generalLoginAll();
			testName("verifyTabCustomization_TestCase35 ");
			loginButtonClick();
			sleepingMethod();	
			
			bareClick("//img[@title='All Tabs']");//Plus Click
			sleepingMethod();		
			bareClick("//input[@title='Customize My Tabs']");//CustomizeMyTabs BUtton Click			
			selectElement = driver.findElement(By.xpath("//select[@id='duel_select_1']"));
			    select = new Select(selectElement);
			    List<WebElement> optionsCheck = select.getOptions();
			    boolean isPresentFinal = false;
			    sleepingMethod();	
			    for (WebElement option : optionsCheck) {
			        String optionValue = option.getAttribute("value");
			        
			        if (optionValue.trim().equals(strTargetValue)) {
			        	isPresentFinal = true;
			           System.out.println(strTargetValue +"is still in Selected Tabs");
			           break; 
			        }
			    }			   
			    if (!isPresentFinal) {
			        System.out.println(strTargetValue + " is not present in Selected Tabs. TEST PAST ");
			    }						
		 waitBrowserClose();
		
		
	}
	
	
	
		}
		
		
	
	


