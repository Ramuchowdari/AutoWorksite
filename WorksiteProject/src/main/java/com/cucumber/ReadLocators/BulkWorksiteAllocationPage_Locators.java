package com.cucumber.ReadLocators;

import java.util.Map;

import org.openqa.selenium.By;

import com.cucumber.steps.ParentStep;
import com.cucumber.utility.ExcelReade;

public class BulkWorksiteAllocationPage_Locators{
	
	Map <String, String> LocatorData;
	public static String allocation_menu_item  ;
	public static String bulk_allocation_worksites_to_adviser  ;
	public static String branch_input  ;
	public static String to_adviser_input  ;
	public static String branch_suggestion_option  ;
	public static String adviser_suggestion_option  ;
	public static String adviser  ;
	public static String worksite_to_allocate  ;
	public static String allocate_btn  ;
	public static String allocate_dialog  ;
	public static String confirmation_yes_btn  ;
	public static String allocate_confirmation_message  ;
	public static String next_btn  ;
	
	
	
	{
		String locatorFile=System.getProperty("ResourcesBaseFolder")+System.getProperty("LocatorsBaseFolder")+ this.getClass().getSimpleName()+".xlsx";
		
		ExcelReade readData= new ExcelReade();
		try {		
			LocatorData = readData.getLocatorData(locatorFile, "Locators");
			allocation_menu_item =LocatorData.get("allocation_menu_item");
			bulk_allocation_worksites_to_adviser =LocatorData.get("bulk_allocation_worksites_to_adviser");
			branch_input =LocatorData.get("branch_input");
			to_adviser_input =LocatorData.get("to_adviser_input");
			branch_suggestion_option =LocatorData.get("branch_suggestion_option");
			adviser_suggestion_option =LocatorData.get("adviser_suggestion_option");
			adviser =LocatorData.get("adviser");
			worksite_to_allocate =LocatorData.get("worksite_to_allocate");
			allocate_btn =LocatorData.get("allocate_btn");
			allocate_dialog =LocatorData.get("allocate_dialog");
			confirmation_yes_btn =LocatorData.get("confirmation_yes_btn");
			allocate_confirmation_message =LocatorData.get("allocate_confirmation_message");
			next_btn =LocatorData.get("next_btn");
	
			
		}
			
			catch (Exception e) 
		{
			e.printStackTrace();			
			System.err.println("File Not Found "+locatorFile);		
			
		}
	}
	@Override
	public String toString() {
		return "BulkWorksiteAllocationPage_Locators[getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}