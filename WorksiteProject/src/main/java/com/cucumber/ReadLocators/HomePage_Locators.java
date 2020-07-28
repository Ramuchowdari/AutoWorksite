package com.cucumber.ReadLocators;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cucumber.steps.ParentStep;
import com.cucumber.utility.ExcelReade;

public class HomePage_Locators {
	Map <String, String> LocatorData;
	public static String SEARCH_TEXTBOX ;	
	public static String SEARCH_BTN ;
	
	
	public static String Username;
	
	public static String Password;
	
	public static String Login;
	
	public static String PlansTab;
	
	public static String logout;
	
	public static String loginLink;
	{
		String locatorFile=System.getProperty("ResourcesBaseFolder")+System.getProperty("LocatorsBaseFolder")+ this.getClass().getSimpleName()+".xlsx";
		
		ExcelReade readData= new ExcelReade();
		try {			
			LocatorData = readData.getLocatorData(locatorFile, "Locators");
			Username =LocatorData.get("Username");
			Password =LocatorData.get("Password");
			Login =LocatorData.get("Login");
			PlansTab =LocatorData.get("PlansTab");
			logout =LocatorData.get("logout");
			loginLink =LocatorData.get("loginLink");
			
		}
		catch (Exception e) {			e.printStackTrace();			System.out.println("File Not Found "+locatorFile);		}
	}
	@Override
	public String toString() {
		return "GoogleLocators [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}
