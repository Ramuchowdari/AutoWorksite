package com.cucumber.ReadLocators;

import java.util.Map;

import java.awt.AWTException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.openqa.selenium.By;

import com.cucumber.steps.ParentStep;
import com.cucumber.utility.ExcelReade;

public class CaptureNewOrganisationStructureDetailsPage_Locators{
	
	Map <String, String> LocatorData;
	
	public static String Captureneworgdetails_header;
	public static String Company_input;
	public static String Buildingtype_select;
	public static String Subsidiaryordivision_input;
	public static String autocomplete_input_option;
	public static String WorksiteName_input;
	public static String worksitelocation_input;
	public static String Sector_select;
	public static String Industry_select;
	public static String Street1_input;
	public static String Suburbs_input;
	public static String suburbs_input_options;
	public static String Towns_select;
	public static String Cities_select;
	public static String Province_select;
	public static String Postalcode_input;
	public static String Telephone_input;
	public static String Emailaddress_input;
	public static String Save_btn;
	public static String NewOrganisationStructureSaveConfirmation;
	public static String Cancel_btn;
	public static String addnewcompany_btn;
	public static String company_input_org;
	public static String edit_button;
	public static String current_selected_buildingtype;
	public static String updated_worksite_location_and_name;
	public static String suburb__suggestion;
	public static String save_edit_button;
	{
		String locatorFile=System.getProperty("ResourcesBaseFolder")+System.getProperty("LocatorsBaseFolder")+ this.getClass().getSimpleName()+".xlsx";
		
		ExcelReade readData= new ExcelReade();
		try {			
			LocatorData = readData.getLocatorData(locatorFile, "Locators");
			 Captureneworgdetails_header =LocatorData.get("captureneworgdetails_header");
			 Company_input =LocatorData.get("company_input");
	         Buildingtype_select =LocatorData.get("buildingtype_select");
			 Subsidiaryordivision_input =LocatorData.get("subsidiaryordivision_input");
			 autocomplete_input_option =LocatorData.get("autocomplete_input_option");
			 WorksiteName_input =LocatorData.get("worksitename_input");
			 worksitelocation_input = LocatorData.get("worksitelocation_input");
			 Sector_select =LocatorData.get("sector_select");
			 Industry_select =LocatorData.get("industry_select");
			 Street1_input =LocatorData.get("street1_input"); 
			 Suburbs_input =LocatorData.get("suburbs_input");
			 suburbs_input_options =LocatorData.get("suburbs_input_options");
			 Towns_select =LocatorData.get("towns_select");
			 Cities_select =LocatorData.get("cities_select");
			 Province_select =LocatorData.get("province_select");
			 Postalcode_input =LocatorData.get("postalcode_input");
			 Telephone_input =LocatorData.get("telephone_input");
			 Emailaddress_input =LocatorData.get("emailaddress_input");
			 Save_btn =LocatorData.get("save_btn");
			 NewOrganisationStructureSaveConfirmation = LocatorData.get("save_confirmation");
			 Cancel_btn =LocatorData.get("cancel_btn");
			 addnewcompany_btn =LocatorData.get("addnewcompany_btn");
			 company_input_org =LocatorData.get("company_input_org");
			 edit_button =LocatorData.get("edit_button");
			 current_selected_buildingtype =LocatorData.get("current_selected_buildingtype");
			 updated_worksite_location_and_name = LocatorData.get("updated_worksite_location_and_name");
			 suburb__suggestion = LocatorData.get("suburb__suggestion");
			 save_edit_button = LocatorData.get("save_edit_button");

		}
		catch (Exception e) 
		{
			e.printStackTrace();			
			System.out.println("File Not Found "+locatorFile);		
			
		}
	}
	@Override
	public String toString() {
		return "CaptureNewOrganisationDetailsPageLocators [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}