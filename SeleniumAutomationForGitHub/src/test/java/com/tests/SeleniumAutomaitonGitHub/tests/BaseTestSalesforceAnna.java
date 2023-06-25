package com.tests.SeleniumAutomaitonGitHub.tests;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

//import firebaseAnna.ConfigReader;



import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestSalesforceAnna extends PropertiesUtilitiesSalesforce
{
	public static WebDriver driver=null;
	protected WebDriverWait wait;
	
	public static void launchBrowser(String browserName) throws InterruptedException {
		if(browserName.equalsIgnoreCase("firefox")) {
			Thread.sleep(3000);
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		else if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(browserName.equalsIgnoreCase("opera")) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
			driver.manage().window().maximize();
		}
	}
	
	public static void generalLoginAll() throws InterruptedException, IOException {
		String expectedTitle="Login | Salesforce";
		launchBrowser("chrome");
		//launchBrowser("firefox");
		//launchBrowser("opera");
		
		
		System.out.println("Selected browser was opened");
		driver.get(getUrl());
		
		
		System.out.println("url entered");
		String actualTitle = driver.getTitle();
		if (expectedTitle.equals(actualTitle)) {
			System.out.println("title matched");
		} else {
			System.out.println("title is not matched");
		}
		
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait
		.until(ExpectedConditions.visibilityOfElementLocated(By
		.xpath("//input[@id='username']")));
		System.out.println("Explicit wait for email input worked");

		By userName=By.id("username");
		WebElement userNameElement= driver.findElement(userName);
		userNameElement.clear();
		userNameElement.sendKeys(getUsername());
		
		By password=By.id("password");
		WebElement passwordElement= driver.findElement(password);
		passwordElement.clear();
		passwordElement.sendKeys(getPassword());										
	}
	
	public static void launchLogInWebPage() throws InterruptedException, IOException {
		String expectedTitle="Login | Salesforce";
		launchBrowser("chrome");
		//launchBrowser("firefox");
		//launchBrowser("opera");
		System.out.println("Selected browser was opened");
		driver.get(getUrl());
		
		
		System.out.println("url entered");
		String actualTitle = driver.getTitle();
		if (expectedTitle.equals(actualTitle)) {
			System.out.println("title matched");
		} else {
			System.out.println("title is not matched");
		}
		waitForLoginPage();								
	}
	
	public static void logInFromBrowserPage() throws IOException, InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait
		.until(ExpectedConditions.visibilityOfElementLocated(By
		.xpath("//input[@id='username']")));
		System.out.println("Explicit wait for email input worked");

		By userName=By.id("username");
		WebElement userNameElement= driver.findElement(userName);
		userNameElement.clear();
		userNameElement.sendKeys(getUsername());
		
		By password=By.id("password");
		WebElement passwordElement= driver.findElement(password);
		passwordElement.clear();
		passwordElement.sendKeys(getPassword());	
		loginButtonClick();
		
	}
	
	public static void wrongUsernamewrongPasswordInput() throws IOException {
		waitForLoginPage();

		By userName=By.id("username");
		WebElement userNameElement= driver.findElement(userName);
		userNameElement.clear();
		userNameElement.sendKeys(getInvalidUsername());
		
		By password=By.id("password");
		WebElement passwordElement= driver.findElement(password);
		passwordElement.clear();
		passwordElement.sendKeys(getInvalidPassword());			
	}
	
	public static void userNameInput() throws IOException {
		waitForLoginPage();

		By userName=By.id("username");
		WebElement userNameElement= driver.findElement(userName);
		userNameElement.clear();
		userNameElement.sendKeys(getUsername());		
	}
	
	public static void passwordClear() {
		By password=By.id("password");
		WebElement passwordElement= driver.findElement(password);
		passwordElement.clear();
			
		
	}
	
	public static CharSequence getUserNameData() throws IOException {
		String str = getUsername();
		CharSequence cs = str;		
		return cs;								
	}
	
	public static void userNameCheckInUsernameField() throws IOException {
		String expactedTitle = getUsername();
		
		By userName=By.id("username");
		WebElement userNameElement= driver.findElement(userName);
		
		String actualTitle = userNameElement.getText();
		
		//if (actualTitle==expactedTitle) {
			//System.out.println("Expected Username in Username Field after RememberMe Click Works!");
			
		//}
		SoftAssert softAssertion= new SoftAssert();
		softAssertion.assertEquals(actualTitle, expactedTitle, getUsername());
		System.out.println("SoftAssert Passed");
	}
	
	public static void loginButtonClick()throws InterruptedException  {
		By button= By.id("Login");
		WebElement buttonElement= driver.findElement(button);
		buttonElement.click();
		System.out.println("Login Button Clicked");				
	}
						
	public static void logOutClick(String logOutPath)throws InterruptedException  {
		By logOut=By.xpath(logOutPath);
		WebElement logOutOption=driver.findElement(logOut);
		logOutOption.click();
		System.out.println("Logout Click Worked");	
		waitForLoginPage();		
	}
	
	//--------------------Error Message ----------------------------
	public static void  errorDisplay(String errorElementXpath) {
		WebElement element=driver.findElement(By.xpath(errorElementXpath));
		element.isDisplayed();
		System.out.println("Error message is "+ element.getText());
		
	}
	
	//---------------------expected title of page check -------------------
	public static void checkTitleOfPage(String expectedTitle) {
		String actualTitle = driver.getTitle();
		if (expectedTitle.equals(actualTitle)) {
			System.out.println("title matched");
		} else {
			System.out.println("title is not matched");
		};
	}
	
	//-------------------IS DISPLAYED CHECK  ----------------------
	public static void isElementDisplayed(String xpathOfElementToCheckIfDisplayed) {
		 WebElement elem=driver.findElement(By.xpath(xpathOfElementToCheckIfDisplayed));
		 elem.isDisplayed();
		 focusSet(elem);
		 
	}
	
	public static void focusSet(WebElement element) {
		element.sendKeys("");
	}
	
	
	//--------------------CLICK ----------------------
	
	public static void bareClick(String xPathOfWebElement) {
		
		WebElement element=driver.findElement(By.xpath(xPathOfWebElement));
		element.isDisplayed();
		element.click();		
	}
	
	public static void clickButton(WebElement otherButtonElement) {
		if (otherButtonElement.isDisplayed()) {
			otherButtonElement.click();;
			sleepingMethod();	
			System.out.println(otherButtonElement + "is clicked");
			
		} else {
			System.out.println(otherButtonElement + " is not displayed");
		}
	}
	
	public static void clickAndHoldElement(WebElement webElement) {
		if (webElement.isDisplayed()) {
			Actions actions = new Actions(driver);
		    actions.moveToElement(webElement).clickAndHold().build().perform();
			System.out.println("Webelement is clicked and hold");
			
		} else {
			System.out.println("Webelement is not clicked and hold");
		}
	}
	
	
	//------------------Work with Frame ----------------------------------------
	public static void workWithFrame(String pathOfFrame) {
		waitAnyElement(pathOfFrame);
		WebElement iframe1 = driver.findElement(By.xpath(pathOfFrame));
		driver.switchTo().frame(iframe1);	
		
		
	}
	
	//----------------------------POP UP WINDOW HANDLE -----------------------------
	
	public static void popUpWindowHandle() throws InterruptedException {
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
	}
	
	
	//-------------------------- opportunities ------------------------------------
	public static void opprtunityHomePageOption(String opportunityPath){
		waitAnyElement(opportunityPath);	
		By opportunityBtn=By.xpath("//a[@title='Opportunities Tab']");
		WebElement opportunityHomePageBtn=driver.findElement(opportunityBtn);						
		opportunityHomePageBtn.isDisplayed();
		opportunityHomePageBtn.click();	
		sleepingMethodWithStringMessage("Opportunity Home Page is Displayed");
	}
	
	
	public static void sendKeysFunctionalityByXPath(String textBoxPath, String textInputInfo) {
		waitAnyElement(textBoxPath);	
		By inputViewName=By.xpath(textBoxPath);
		WebElement inputViewNameTxt=driver.findElement(inputViewName);
		inputViewNameTxt.isDisplayed();
		inputViewNameTxt.clear();
		sleepingMethod();
		inputViewNameTxt.sendKeys(textInputInfo);
		sleepingMethodWithStringMessage(textInputInfo + " was successfully insert");
	}
	
	
	//-------------------------DROP DOWN SELECT -----------------------------------
	public static void dropdownHandle(String dropdownName, String xpathOfDropDown, String dropDownOption){	
		 Select se = new Select(driver.findElement(By.xpath(xpathOfDropDown)));
		 se.selectByIndex(3);		 
		 sleepingMethod();    
         System.out.println("Selected option with value 3 was selected"); 

	}
	
	public static String selectOption(String xpathOfDropDown){	
		 Select se = new Select(driver.findElement(By.xpath(xpathOfDropDown)));
		    List<WebElement> options = se.getOptions();
		    WebElement lastOption = options.get(options.size() - 1);

		    // Select the last option
		    se.selectByIndex(options.size() - 1);
		    //clickButton(lastOption);
		    clickAndHoldElement(lastOption);
		    sleepingMethod();	
		    String selectedValue = lastOption.getAttribute("value");
		    System.out.println("Selected value for deleting: " + selectedValue);

		    // Get the selected option text
		    String selectedText = lastOption.getText();
		    System.out.println("Selected text: " + selectedText);

		    return selectedText;
       

	}
	
	
	
	
		public static void dropdownHandleByValue(String dropdownName, String xpathOfDropDown, String dropDownOption){		
			 Select se = new Select(driver.findElement(By.xpath(xpathOfDropDown)));
			 se.selectByVisibleText(dropDownOption);	       	       
			 sleepingMethod();	     
	         System.out.println("Selected option: "+ dropDownOption ); 

		}
	
	//---------------------POSITION OF ELEMENT ON THE SCREEN --------------------------
		public static void elementPosition(WebElement element) {
			Point elementLocation = element.getLocation();
			int elementXCoordinate = elementLocation.getX();

			int screenWidth = driver.manage().window().getSize().getWidth();
			int screenLeftEdgeThreshold = screenWidth / 2; // Assuming left side is up to half of the screen

			if (elementXCoordinate < screenLeftEdgeThreshold) {
			    System.out.println("The element is on the left side of the screen.");
			} else {
			    System.out.println("The element is not on the left side of the screen.");
			}
		}
	
	//----------------------SOFT ASSERT -----------------------------------------
		public static void softAssertEquality(String expectedText, String actualText ) {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(expectedText, actualText);
			System.out.println("Assert Passed");
			
		}
		
		
		
		
	//------------------------WAIT-----------------------------------------------
	
	public static void waitAnyElement(String elementPath) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait
		.until(ExpectedConditions.visibilityOfElementLocated(By
		.xpath(elementPath)));
		System.out.println("Explicit wait for AnyElement worked");	
		
	}
	
	public static void waitForHomePage() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait
		.until(ExpectedConditions.visibilityOfElementLocated(By
		.xpath("//a[@title='Home Tab']")));
		System.out.println("Explicit wait for Home page worked");	
	}
	
	public static void waitForLoginPage() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait
		.until(ExpectedConditions.visibilityOfElementLocated(By
		.xpath("//input[@id='Login']")));
		System.out.println("Explicit wait for Login page worked");	
	}
	
	public static void sleepingMethod(){
		try {
	         TimeUnit.SECONDS.sleep(3);
	         System.out.println("Browser is sleeping for 3 seconds. ");
	     } catch (InterruptedException e) {
	         e.printStackTrace();
	     }
	}
	
	public static void sleepingMethodWithStringMessage(String str){
		try {
	         TimeUnit.SECONDS.sleep(3);
	         System.out.println(str +" was displayed");
	     } catch (InterruptedException e) {
	         e.printStackTrace();
	     }
	}
	
	 
	public static void waitBrowserClose()  {
		
        try {
            TimeUnit.SECONDS.sleep(4);
            System.out.println("Browser is sleeping for 4 seconds. TEST PASSED");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
}
	
    public static void browserCloseTestFailed()  {
		
        try {
            TimeUnit.SECONDS.sleep(4);
            System.out.println("TEST FAILED");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
}
	
	public static void testName(String nameOfTest) {
		System.out.println(nameOfTest);
	}
	
	public static void testPassed() {
		System.out.println("TEST PASSED");
	}
}
