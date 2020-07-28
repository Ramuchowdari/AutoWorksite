package com.cucumber.ReadLocators;

import java.util.Map;

import org.openqa.selenium.By;

import com.cucumber.steps.ParentStep;
import com.cucumber.utility.ExcelReade;

public class WorksiteHomePage_Locators{
	
	Map <String, String> LocatorData;


	public static String OmlogoImage;
	public static String HomeMenuItem;
	public static String ManageWorkisiteMenuItem;
	public static String AdministrationMenuItem;
	public static String AuthoriseMenuItem;
	public static String CreateWorkSiteMenuItemOption;
	public static String SearchForWorksiteMenuItemOption;
	public static String CreateOrganisationStructureMenuItemOption;
	public static String UserAccessMenuItemOption;
	public static String loader;
	public static String edit_org_structure;
	{
		String locatorFile=System.getProperty("ResourcesBaseFolder")+System.getProperty("LocatorsBaseFolder")+ this.getClass().getSimpleName()+".xlsx";
		
		ExcelReade readData= new ExcelReade();
		try {			
			LocatorData = readData.getLocatorData(locatorFile, "Locators");
			 OmlogoImage =LocatorData.get("omlogo_img");
			 HomeMenuItem =LocatorData.get("home_link");
			 ManageWorkisiteMenuItem =LocatorData.get("manageworksite_select");
			 AdministrationMenuItem =LocatorData.get("adminstration_select");
			 AuthoriseMenuItem =LocatorData.get("authorize_link");
		     CreateWorkSiteMenuItemOption =LocatorData.get("createworksite_select_option");
			 SearchForWorksiteMenuItemOption =LocatorData.get("searchworksite_select_option");
			 CreateOrganisationStructureMenuItemOption =LocatorData.get("createorganisationstructure_select_option");
			 UserAccessMenuItemOption =LocatorData.get("useraccess_select_option");
			 loader=LocatorData.get("loader");
			 edit_org_structure =LocatorData.get("edit_org_structure");
		}
		catch (Exception e) 
		{
			e.printStackTrace();			
			System.out.println("File Not Found "+locatorFile);		
			
		}
	}
	@Override
	public String toString() {
		return "WorksiteHomePageLocators [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}