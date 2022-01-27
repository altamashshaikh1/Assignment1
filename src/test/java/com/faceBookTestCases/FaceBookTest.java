package com.faceBookTestCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.automation.base.CommonActions;

public class FaceBookTest extends CommonActions 
{
	public static WebDriver driver;

	@BeforeClass
	public void pageFactoryInitialization()
	{
		//Initialize page objects
		intializePageFactory(driver,"com.faceBookPageObjects.Locators");
	}

	@Parameters({"Environment","Browser"})
	@BeforeTest
	public void beforeTest(String environment,String browser) throws Exception
	{    	   		
		//Driver initialization
		driver = getDriver(browser);
		driver.get(appURL(environment));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(80, TimeUnit.SECONDS);
	}

	@Parameters({"Environment","Browser"})
	@Test(priority = 0,groups = "Facebook - User login",testName = "Login to facebook account",description="Verify facebook account login")
	public void loginUser(String environment,String browser) throws Exception
	{
		waitForElementVisible(com.faceBookPageObjects.Locators.login_block_1,driver,"Wait");
		inputbox(com.faceBookPageObjects.Locators.emailTextBox,getEmaildId(environment),driver,"Input user email");
		inputbox(com.faceBookPageObjects.Locators.password,getPassword(environment),driver,"Input user password");
		click(com.faceBookPageObjects.Locators.loginButton,driver,"Click on login button");
		waitForElementVisible(com.faceBookPageObjects.Locators.facebookMenuString, driver, "Wait for user dashboard to load");
		elementvisible(com.faceBookPageObjects.Locators.facebookMenu, driver, "Verify user logged in");
	}
    
	@Parameters({"Environment","Browser"})
	@Test(dependsOnMethods = {"loginUser"}, priority = 1,groups = "Facebook - Post Status",testName = "Post status to wall",description="Verify new status on wall")
	public void postStatus(String environment,String browser) throws Exception
	{
		//Soft assertion variable
		SoftAssert softAssertion = new SoftAssert();

		click(com.faceBookPageObjects.Locators.facebookMenu, driver, "Click on facebook menu to post new status");
        waitForElementVisible(com.faceBookPageObjects.Locators.postStatusLinkString, driver,"Wait for post text box to be visible");
		click(com.faceBookPageObjects.Locators.postStatusOption, driver, "Click on post new status option");
		waitForElementVisible(com.faceBookPageObjects.Locators.postModalString, driver,"Wait for post status modal to be visible");
        click(com.faceBookPageObjects.Locators.postStatusTextBox, driver, "Click on status box");
		keyboardActions(driver, "Hello World", "Enter post", softAssertion);
        click(com.faceBookPageObjects.Locators.postButton, driver, "Click on Post button");
		waitForElementVisible(com.faceBookPageObjects.Locators.wallPostString, driver, "Wait for facebook wall dashboard to be visible");
		elementvisible(com.faceBookPageObjects.Locators.wallPost, driver, "Assert current status post be visible");
		
		//Collect all soft assertions if any
		softAssertion.assertAll();
	}

	@Parameters({"Environment","Browser"})
	@AfterMethod
	public void afterMethod(ITestResult arg,String environment,String browser) throws IOException
	{ 			  
		captureConsoleMessages(driver,"\""+arg.getMethod().getDescription()+"\"", browser); 	
	}
    
	@AfterTest
	public void afterTest() 
	{
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();	
	}
}