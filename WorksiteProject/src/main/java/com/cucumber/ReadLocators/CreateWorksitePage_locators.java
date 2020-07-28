package com.cucumber.ReadLocators;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cucumber.steps.ParentStep;
import com.cucumber.utility.ExcelReade;

public class CreateWorksitePage_locators {
	Map <String, String> LocatorData;
	public static String save_btn;
	public static String createworksite_header;
	public static String workSiteTypeGeneric_radio;
	public static String workSiteTypeOffical_radio;
	public static String workSiteTypeUnOffical_radio;
	public static String holdingcompany_input;
	public static String debitorderaccess_radio;
	public static String debitorderemployeeeprofile_radio;
	public static String stoporderaccessagreement_radio;
	public static String stoporderemployeeprofiledocument_radio;
	public static String next_btn;
	public static String nextRD_btn;
	public static String nextAB_btn;
	public static String nextWP_btn;
	public static String nextUD_btn;
	public static String nextPO_btn;
	public static String nextAA_btn;
	public static String relationshipdetails_select;
	public static String jobtitle_input;
	public static String title_select;
	public static String name_input;
	public static String surname_input;
	public static String mobilenumber_input;
	public static String emailaddress_input;
	public static String addanotherstakeholder_btn;
	public static String relationshipdetailssetion_next_btn;
	public static String allocatebranch_next_btn;
	public static String salesbranch_select;
	public static String totalamountofpeople_input;
	public static String incomebracketrange1_input;
	public static String incomebracketrange2_input;
	public static String incomebracketrange3_input;
	public static String incomebracketrange4_input;
	public static String incomebracketrange5_input;
	public static String incomebracketrange6_input;
	public static String rmmpotential_input;
	public static String percentagefield1_input;
	public static String percentagefield2_input;
	public static String percentagefield3_input;
	public static String percentagefield4_input;
	public static String percentagefield5_input;
	public static String percentagefield6_input;
	public static String worksitePotentialSection_next_btn;
	public static String selectschemenameorcode_select;
	public static String addlist_btn;
	public static String addToListPO;
	public static String schemecodeorname_select;
	public static String schemesearchresults_select_option;
	public static String schemecodeorname_table;
	public static String uniondetails_next_btn;
	public static String paymentmethod_select;
	public static String autocomplete_input_option;
	public static String subsidiaryordivision_input;
	public static String worksiteName_input ;
	public static String toast_msg ;
	public static String advisor_checkbox;
	public static String advisor_checkboxs;
	public static String tab_relationshipDetails;
	public static String tab_allocateAdvisor;
	public static String createworksite_btn;
	{
		String locatorFile=System.getProperty("ResourcesBaseFolder")+System.getProperty("LocatorsBaseFolder")+ this.getClass().getSimpleName()+".xlsx";
		
		ExcelReade readData= new ExcelReade();
		try {			
			LocatorData = readData.getLocatorData(locatorFile, "Locators");
			
			createworksite_header=LocatorData.get("createworksite_header");
			workSiteTypeGeneric_radio=LocatorData.get("workSiteTypeGeneric_radio");
			workSiteTypeOffical_radio=LocatorData.get("workSiteTypeOffical_radio");
			workSiteTypeUnOffical_radio=LocatorData.get("workSiteTypeUnOffical_radio");
			holdingcompany_input=LocatorData.get("holdingcompany_input");
			debitorderaccess_radio=LocatorData.get("debitorderaccess_radio");
			debitorderemployeeeprofile_radio=LocatorData.get("debitorderemployeeeprofile_radio");
			stoporderaccessagreement_radio=LocatorData.get("stoporderaccessagreement_radio");
			stoporderemployeeprofiledocument_radio=LocatorData.get("stoporderemployeeprofiledocument_radio");
			next_btn=LocatorData.get("next_btn");
			nextRD_btn =LocatorData.get("nextRD_btn");
			nextAB_btn =LocatorData.get("nextAB_btn");
			nextWP_btn =LocatorData.get("nextWP_btn");
			nextUD_btn =LocatorData.get("nextUD_btn");
			nextPO_btn =LocatorData.get("nextPO_btn");
			nextAA_btn =LocatorData.get("nextAA_btn");
			relationshipdetails_select=LocatorData.get("relationshipdetails_select");
			jobtitle_input=LocatorData.get("jobtitle_input");
			title_select=LocatorData.get("title_select");
			name_input=LocatorData.get("name_input");
			surname_input=LocatorData.get("surname_input");
			mobilenumber_input=LocatorData.get("mobilenumber_input");
			emailaddress_input=LocatorData.get("emailaddress_input");
			addanotherstakeholder_btn=LocatorData.get("addanotherstakeholder_btn");
			relationshipdetailssetion_next_btn=LocatorData.get("relationshipdetailssetion_next_btn");
			allocatebranch_next_btn=LocatorData.get("allocatebranch_next_btn");
			salesbranch_select=LocatorData.get("salesbranch_select");
			totalamountofpeople_input=LocatorData.get("totalamountofpeople_input");
			incomebracketrange1_input=LocatorData.get("incomebracketrange1_input");
			incomebracketrange2_input=LocatorData.get("incomebracketrange2_input");
			incomebracketrange3_input=LocatorData.get("incomebracketrange3_input");
			incomebracketrange4_input=LocatorData.get("incomebracketrange4_input");
			incomebracketrange5_input=LocatorData.get("incomebracketrange5_input");
			incomebracketrange6_input=LocatorData.get("incomebracketrange6_input");
			rmmpotential_input=LocatorData.get("rmmpotential_input");
			percentagefield1_input=LocatorData.get("percentagefield1_input");
			percentagefield2_input=LocatorData.get("percentagefield2_input");
			percentagefield3_input=LocatorData.get("percentagefield3_input");
			percentagefield4_input=LocatorData.get("percentagefield4_input");
			percentagefield5_input=LocatorData.get("percentagefield5_input");
			percentagefield6_input=LocatorData.get("percentagefield6_input");
			worksitePotentialSection_next_btn=LocatorData.get("worksitePotentialSection_next_btn");
			selectschemenameorcode_select=LocatorData.get("selectschemenameorcode_select");
			addlist_btn=LocatorData.get("addlist_btn");
			addToListPO=LocatorData.get("addToListPO");
			schemecodeorname_select=LocatorData.get("schemecodeorname_select");
			schemesearchresults_select_option=LocatorData.get("schemesearchresults_select_option");
			schemecodeorname_table=LocatorData.get("schemecodeorname_table");
			uniondetails_next_btn=LocatorData.get("uniondetails_next_btn");
			paymentmethod_select=LocatorData.get("paymentmethod_select");
			autocomplete_input_option=LocatorData.get("autocomplete_input_option");
			subsidiaryordivision_input=LocatorData.get("subsidiaryordivision_input");
			worksiteName_input=LocatorData.get("worksiteName_input");
			toast_msg=LocatorData.get("toast_msg");
			advisor_checkbox=LocatorData.get("advisor_checkbox");
			advisor_checkboxs=LocatorData.get("advisor_checkboxs");
			save_btn=LocatorData.get("save_btn");
			tab_relationshipDetails=LocatorData.get("tab_relationshipDetails");
			tab_allocateAdvisor=LocatorData.get("tab_allocateAdvisor");
			createworksite_btn =LocatorData.get("createworksite_btn");
		}
		catch (Exception e) {			e.printStackTrace();			System.out.println("File Not Found "+locatorFile);		}
	}
	@Override
	public String toString() {
		return "GoogleLocators [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}
