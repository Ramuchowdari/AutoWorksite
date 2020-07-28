package com.cucumber.ReadLocators;

import java.util.Map;

import org.openqa.selenium.By;

import com.cucumber.steps.ParentStep;
import com.cucumber.utility.ExcelReade;

public class SearchWorksitesPage_Locators extends WorksiteHomePage_Locators{
	
	Map <String, String> LocatorData;
	public static String next;
	public static String searchforworksite    ;
	public static String search_link          ;
	public static String worksitetype_select  ;
	public static String searchworksitetable  ;
	public static String searchworksiterows  ;
	public static String searchworksitecolumns  ;
	public static String worksitetoconvert  ;
	public static String worksitetoedit  ;
	public static String isthisareview_radio  ;
	public static String isthisanepdchange_radio  ;
	public static String ok_btn  ;
	public static String editandviewworksiteheader  ;
	public static String organisationstructure_link  ;
	public static String relationshipdetails_link  ;
	public static String allocatebranch_link  ;
	public static String worksitepotential_link  ;
	public static String uniondetails_link  ;
	public static String payoffice_link  ;
	public static String allocateadvisors_link  ;
	public static String save_btn  ;
	public static String close_btn  ;
	public static String convert_btn  ;
	public static String next_btn  ;
	public static String relationshipdetails_lbl  ;
	public static String salesbranch_lbl  ;
	public static String paymentmethod_select_option  ;
	public static String addtolist_btn  ;
	public static String salesbranch_select_option  ;
	public static String search_category_select  ;
	public static String search_term_input  ; 
	public static String official_non_official_dropdown_item;
	public static String loader_spin;
	public static String payofficedetails_exists;
	public static String uniondetails_exists;
	public static String isbranch_selected;
	public static String check_status;
	public static String status;
	public static String search_term_auto_suggestion;
	public static String allocate_advisor_radio;
	
//	public static String loader  ;
	{
		String locatorFile=System.getProperty("ResourcesBaseFolder")+System.getProperty("LocatorsBaseFolder")+ this.getClass().getSimpleName()+".xlsx";
		
		ExcelReade readData= new ExcelReade();
		try {			
			LocatorData = readData.getLocatorData(locatorFile, "Locators");
			searchforworksite =LocatorData.get("searchforworksite_header"); 
			search_link =LocatorData.get("search_link"); 
			worksitetype_select =LocatorData.get("worksitetype_select"); 
			searchworksitetable =LocatorData.get("searchworksitetable"); 
			searchworksiterows =LocatorData.get("searchworksiterows"); 
			searchworksitecolumns =LocatorData.get("searchworksitecolumns"); 
			worksitetoconvert =LocatorData.get("worksitetoconvert"); 
			worksitetoedit =LocatorData.get("worksitetoedit"); 
			isthisareview_radio =LocatorData.get("isthisareview_radio"); 
			isthisanepdchange_radio =LocatorData.get("isthisanepdchange_radio"); 
			ok_btn =LocatorData.get("ok_btn"); 
			editandviewworksiteheader =LocatorData.get("editandviewworksiteheader"); 
			organisationstructure_link =LocatorData.get("organisationstructure_link"); 
			relationshipdetails_link =LocatorData.get("relationshipdetails_link"); 
			allocatebranch_link =LocatorData.get("allocatebranch_link"); 
			worksitepotential_link =LocatorData.get("worksitepotential_link"); 
			uniondetails_link =LocatorData.get("uniondetails_link"); 
			payoffice_link =LocatorData.get("payoffice_link"); 
			allocateadvisors_link =LocatorData.get("allocateadvisors_link"); 
			save_btn =LocatorData.get("save_btn"); 
			close_btn =LocatorData.get("close_btn"); 
			next =LocatorData.get("next");
			convert_btn =LocatorData.get("convert_btn");
			next_btn =LocatorData.get("documentation_next");
			relationshipdetails_lbl =LocatorData.get("relationshipdetails_lbl");
			salesbranch_lbl =LocatorData.get("salesbranch_lbl");
			paymentmethod_select_option =LocatorData.get("paymentmethod_select_option");
			addtolist_btn =LocatorData.get("addtolist_btn");
			salesbranch_select_option =LocatorData.get("salesbranch_select_option");
			search_category_select =LocatorData.get("search_category_select");
			search_term_input =LocatorData.get("search_term_input"); 
			official_non_official_dropdown_item =LocatorData.get("official_non_official_dropdown_item");
			loader_spin =LocatorData.get("loader");
			payofficedetails_exists =LocatorData.get("payofficedetails_exists");
			uniondetails_exists =LocatorData.get("uniondetails_exists");
			isbranch_selected =LocatorData.get("isbranch_selected");
			check_status = LocatorData.get("check_status");
			status = LocatorData.get("status");
			search_term_auto_suggestion = LocatorData.get("search_term_auto_suggestion");
			allocate_advisor_radio = LocatorData.get("allocate_advisor_radio");
		}
		catch (Exception e)  
		{
			e.printStackTrace();			
			System.out.println("File Not Found "+locatorFile);		
		}
	}
	@Override
	public String toString() {
		return "SearchWorksitePageLocators [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}	
}