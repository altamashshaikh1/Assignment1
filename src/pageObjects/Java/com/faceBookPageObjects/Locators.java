package com.faceBookPageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Locators 
{
	public static String login_block_1 = "//input[@id='email']";

	@FindBy(id = "email")
	public static WebElement login_block;

	@FindBy(id = "email")
	public static WebElement emailTextBox;

	@FindBy(id = "pass")
	public static WebElement password;

	@FindBy(xpath = "//button[@name='login']")
	public static WebElement loginButton;
    
	public static String facebookMenuString = "//div[@aria-label='Menu']";
			
	@FindBy(xpath = "//div[@aria-label='Menu']")
	public static WebElement facebookMenu;
			
	public static String postStatusLinkString = "//i[@class='hu5pjgll lzf7d6o1']";
	
	@FindBy(xpath = "//i[@class='hu5pjgll lzf7d6o1']")
	public static WebElement postStatusOption;
	
	public static String postModalString = "//span[text()='Create post']";

	@FindBy(xpath = "//div[@role='textbox']")
	public static WebElement postStatusTextBox;
	
	@FindBy(xpath = "//div[@aria-label='Post']")
	public static WebElement postButton;
	
	public static String wallPostString = "//div[text()='Hello World']";
	
	@FindBy(xpath = "//div[text()='Hello World']")
	public static WebElement wallPost;
}