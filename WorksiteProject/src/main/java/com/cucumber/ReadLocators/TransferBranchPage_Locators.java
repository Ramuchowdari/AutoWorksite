package com.cucumber.ReadLocators;

import java.util.Map;

import org.openqa.selenium.By;

import com.cucumber.steps.ParentStep;
import com.cucumber.utility.ExcelReade;

public class TransferBranchPage_Locators{
	
	Map <String, String> LocatorData;
	public static String transfers_menu_item  ;
	
	public static String branch_to_branch  ;
	public static String from_branch_input  ;
	public static String to_branch_input  ;
	public static String suggestion_option  ;
	public static String transfer_btn  ;
	public static String transfer_confirmation_message ;
	public static String advisers ;
	public static String select_all_btn ;
	public static String yes_btn ;
	public static String dialog ;
	public static String worksite_to_transfer;
	public static String next_button;
	public static String adviser_to_adviser  ;
	public static String from_adviser_input  ;
	public static String from_adviser_suggestion_option  ;
	public static String to_adviser_input  ;
	public static String to_adviser_suggestion_option;
	public static String select_worksite ;
	public static String adviser_transfer_btn  ;
	public static String adviser_dialog ;
	public static String confirmation_yes_btn ;
	public static String loader ;
	
	
	{
		String locatorFile=System.getProperty("ResourcesBaseFolder")+System.getProperty("LocatorsBaseFolder")+ this.getClass().getSimpleName()+".xlsx";
		
		ExcelReade readData= new ExcelReade();
		try {		
			LocatorData = readData.getLocatorData(locatorFile, "Locators");
			transfers_menu_item =LocatorData.get("transfers_menu_item");
			branch_to_branch =LocatorData.get("branch_to_branch"); 
			from_branch_input =LocatorData.get("from_branch_input"); 
			to_branch_input =LocatorData.get("to_branch_input");
			suggestion_option =LocatorData.get("suggestion_option");
			transfer_btn =LocatorData.get("transfer_btn");
			transfer_confirmation_message =LocatorData.get("transfer_confirmation_message");
			advisers =LocatorData.get("advisers");
			select_all_btn =LocatorData.get("select_all_btn");
			yes_btn =LocatorData.get("yes_btn");
			dialog =LocatorData.get("dialog");
			worksite_to_transfer =LocatorData.get("worksite_to_transfer");
			next_button =LocatorData.get("next_button");
			
			adviser_to_adviser =LocatorData.get("adviser_to_adviser");
			from_adviser_input =LocatorData.get("from_adviser_input");
			from_adviser_suggestion_option =LocatorData.get("from_adviser_suggestion_option");
			to_adviser_input =LocatorData.get("to_adviser_input");
			to_adviser_suggestion_option =LocatorData.get("to_adviser_suggestion_option");
			select_worksite =LocatorData.get("select_worksite");
			adviser_transfer_btn =LocatorData.get("adviser_transfer_btn");
			adviser_dialog =LocatorData.get("adviser_dialog");
			confirmation_yes_btn =LocatorData.get("confirmation_yes_btn");
			loader =LocatorData.get("loader");
			
			
			
		
		}
			
			catch (Exception e) 
		{
			e.printStackTrace();			
			System.err.println("File Not Found "+locatorFile);		
			
		}
	}
	@Override
	public String toString() {
		return "TransferBranchPage_Locators [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}