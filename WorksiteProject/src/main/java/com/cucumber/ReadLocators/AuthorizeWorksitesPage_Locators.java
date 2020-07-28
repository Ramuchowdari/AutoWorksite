package com.cucumber.ReadLocators;

import java.util.Map;

import org.openqa.selenium.By;

import com.cucumber.steps.ParentStep;
import com.cucumber.utility.ExcelReade;

public class AuthorizeWorksitesPage_Locators{
	
	Map <String, String> LocatorData;
	public static String authorizeworksites_header  ;
	public static String authorizeworksites_tbl  ;
	public static String authorizeworksite_btn  ;
	public static String worksitetoauthorize  ;
	public static String worksiteauthorisingheader  ;
	public static String canOpenRelationshipDetails_next_btn  ;
	public static String canOpenBranchAllocation_next_btn  ;
	public static String canOpenWorksitePotential_next_btn  ;
	public static String canOpenUnionDetails_next_btn  ;
	public static String canOpenPayOfficeDetails_next_btn  ;
	public static String approve_btn  ;
	public static String reject_btn  ;
	public static String reject_reasons_input  ;
	public static String save_btn  ;
	public static String close_btn  ;
	public static String relationshipdetails_label ;
	public static String salesbranch_label  ;
	public static String totalamountofindividuals_label  ;
	public static String scheme_table  ;
	public static String payofficemethod_table  ;
	public static String successconfirmation  ;
	public static String authorise_not_completed  ;
	public static String authorise_completed_successfully  ;
	public static String loading_spinner;
	public static String worksite_status ;
	{
		String locatorFile=System.getProperty("ResourcesBaseFolder")+System.getProperty("LocatorsBaseFolder")+ this.getClass().getSimpleName()+".xlsx";
		
		ExcelReade readData= new ExcelReade();
		try {		
			LocatorData = readData.getLocatorData(locatorFile, "Locators");
			authorizeworksites_header =LocatorData.get("authorizeworksites_header");
			worksitetoauthorize =LocatorData.get("worksitetoauthorize"); 
			worksiteauthorisingheader =LocatorData.get("worksiteauthorisingheader"); 
			canOpenRelationshipDetails_next_btn =LocatorData.get("canOpenRelationshipDetails_next_btn");
			canOpenBranchAllocation_next_btn =LocatorData.get("canOpenBranchAllocation_next_btn");
			canOpenWorksitePotential_next_btn =LocatorData.get("canOpenWorksitePotential_next_btn");
			canOpenUnionDetails_next_btn =LocatorData.get("canOpenUnionDetails_next_btn");
			canOpenPayOfficeDetails_next_btn =LocatorData.get("canOpenPayOfficeDetails_next_btn");
			approve_btn =LocatorData.get("approve_btn");
			reject_btn =LocatorData.get("reject_btn");
			reject_reasons_input =LocatorData.get("reject_reasons_input");
			save_btn =LocatorData.get("save_btn");
			close_btn =LocatorData.get("close_btn");
			relationshipdetails_label =LocatorData.get("relationshipdetails_label");
			salesbranch_label =LocatorData.get("salesbranch_label");
			totalamountofindividuals_label =LocatorData.get("totalamountofindividuals_label");
			scheme_table =LocatorData.get("scheme_table");
			payofficemethod_table =LocatorData.get("payofficemethod_table");
			successconfirmation =LocatorData.get("successconfirmation");
			authorise_not_completed =LocatorData.get("authorise_not_completed");
			authorise_completed_successfully =LocatorData.get("authorise_completed_successfully");
			loading_spinner =LocatorData.get("loading_spinner");
			worksite_status =LocatorData.get("worksite_status");
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();			
			System.err.println("File Not Found "+locatorFile);		
			
		}
	}
	@Override
	public String toString() {
		return "WorksiteHomePageLocators [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}