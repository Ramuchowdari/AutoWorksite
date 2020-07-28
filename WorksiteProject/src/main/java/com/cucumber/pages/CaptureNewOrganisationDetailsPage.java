package com.cucumber.pages;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.internal.TestMethodWithDataProviderMethodWorker;

import com.cucumber.ReadLocators.CaptureNewOrganisationStructureDetailsPage_Locators;
import com.cucumber.businesslogic.CommonMethod;
import com.cucumber.steps.WorksiteBase;
import com.cucumber.utility.ExcelReade;

/**
 * 
 * @author 
 *
 */

public class CaptureNewOrganisationDetailsPage extends CommonMethod {
	
	private WebDriver driver;
	private WebElement optionElement;

	
	
	Map <String, String> testData;
	CaptureNewOrganisationStructureDetailsPage_Locators locators;
	WorksiteBase worksiteBase;
	
		
	public CaptureNewOrganisationDetailsPage(WebDriver driver, String strDataFile, String strDataSheet) throws IOException{	
		this.driver = driver;
		PageFactory.initElements(driver, this);
		testData = ExcelReade.readDataFromSheet(strDataFile,strDataSheet,System.getProperty("TestID"));
		locators = new CaptureNewOrganisationStructureDetailsPage_Locators();
	}
	
	public void checkCaptureNewOrganisationDetailsHeader(){
		waitForElementToAppear(locators.Captureneworgdetails_header);
		
	}
	
	public void checkNewOrganisationDetailsHaveBeenAdded(){
		waitForElementToAppear(locators.NewOrganisationStructureSaveConfirmation);
	}
	
	public void clickSaveButton(){
		ScrollIntoView(locators.Save_btn);
		userLeftclick(locators.Save_btn);
		
	}
	
	public void captureNewOrganisationDetails() throws InterruptedException{
		
		worksiteBase = new WorksiteBase();
		worksiteBase.setData("Company", testData.get("Company"));
		worksiteBase.setData("Division", testData.get("Subsidiary Or Division"));
		worksiteBase.setData("WorksiteName", testData.get("Worksite Name"));
		
		
		//Holding Company Or Company*
		fillInputValue(locators.Company_input, testData.get("Company"));
		
		//All sleep are put in because of environment being slow (No need for these waits when running while connect to Old Mutual network)
		//It waits for the fields to be populated
		sleepTime(2);
		
		//Building Type*
		selectFromDropdown(locators.Buildingtype_select, testData.get("BuildingType"));
			
		//Street 1*
		fillInputValue(locators.Street1_input, testData.get("Street1"));
		
		//Street 2
		
		waitForPageLoad();
		
		//Suburb
		clickOnElement(locators.Suburbs_input);
		TypeInField(locators.Suburbs_input, testData.get("Suburb"));

		String suburbSuggestion = replaceDataInString(locators.suburbs_input_options,testData.get("Suburb"),testData.get("Suburb"));
		waitForElementToAppear(suburbSuggestion);
		userLeftclick(suburbSuggestion);
		
		sleepTime(2);
		
		//Town
		SelectFromDropdownRandomly(locators.Towns_select);
		
		//City
		SelectFromDropdownRandomly(locators.Cities_select);

		//Province
		SelectFromDropdownRandomly(locators.Province_select);

		//Postal Code
		fillInputValue(locators.Postalcode_input,testData.get("Postal Code"));
		
		//Subsidiary or Division 
		fillInputValue(locators.Subsidiaryordivision_input, testData.get("Subsidiary Or Division"));
		
		//Subsidiary or Division Option
		userLeftclick(locators.autocomplete_input_option);
		
		//Worksite Location
		fillInputValue(locators.WorksiteName_input, testData.get("Worksite Name"));
		
		//Worksite Location
		userLeftclick(locators.autocomplete_input_option);
		
		//Sector
		SelectFromDropdownRandomly(locators.Sector_select);

		//Industry
		SelectFromDropdownRandomly(locators.Industry_select);
		
		//Telephone
		fillInputValue(locators.Telephone_input, testData.get("Telephone"));
		
		//Email 
		fillInputValue(locators.Emailaddress_input, testData.get("Email"));

	}
	
	public void captureBulkOrgStructureDetails() throws InterruptedException {
		
		
		//Holding Company Or Company*
		fillInputValue(locators.Company_input, testData.get("Company"));
		
		sleepTime(2);
		//Building Type*
		selectFromDropdown(locators.Buildingtype_select, testData.get("BuildingType"));
			
		//Street 1*
		fillInputValue(locators.Street1_input, testData.get("Street1"));
		
		//Street 2
		
		waitForPageLoad();
		
		//Suburb
		clickOnElement(locators.Suburbs_input);
		
		//Use normal fill input if environment is stable of decrease the time it waits until it types the next character
		TypeInField(locators.Suburbs_input, testData.get("Suburb"));

		
		String suburbSuggestion =String.format(locators.suburbs_input_options,testData.get("Suburb"),testData.get("Suburb"));
		waitForElementToAppear(suburbSuggestion);
		userLeftclick(suburbSuggestion);
		
		sleepTime(2);
		
		//Town

		SelectFromDropdownRandomly(locators.Towns_select);
		
		//City
		SelectFromDropdownRandomly(locators.Cities_select);

		//Province
		SelectFromDropdownRandomly(locators.Province_select);

		//Postal Code
		fillInputValue(locators.Postalcode_input,testData.get("Postal Code"));
		
		//Subsidiary or Division 
		fillInputValue(locators.Subsidiaryordivision_input, testData.get("Subsidiary Or Division"));
		
		//Subsidiary or Division Option
		userLeftclick(locators.autocomplete_input_option);
		
		//Worksite Location
		fillInputValue(locators.WorksiteName_input, testData.get("Worksite Name"));
		
		//Worksite Location
		userLeftclick(locators.autocomplete_input_option);
		
		//Sector
		SelectFromDropdownRandomly(locators.Sector_select);

		//Industry
		SelectFromDropdownRandomly(locators.Industry_select);
		
		//Telephone
		fillInputValue(locators.Telephone_input, testData.get("Telephone"));
		
		//Email 
		fillInputValue(locators.Emailaddress_input, testData.get("Email"));
		
	}
	
	public void captureOrganisationStructure() throws InterruptedException{
		
		//Holding Company Or Company*
		fillInputValue(locators.Company_input, testData.get("Company"));
		
		//Can be removed | it catering for environment issues where it was not populating dropdown with values
		sleepTime(2);
		
		//Building Type* | testData.get("BuildingType")
		SelectFromDropdownRandomly(locators.Buildingtype_select);
			
		//Street 1*
		fillInputValue(locators.Street1_input, testData.get("Street1"));
		
		//Street 2
		
		waitForPageLoad();
		
		//Suburb
		clickOnElement(locators.Suburbs_input);
		TypeInField(locators.Suburbs_input, testData.get("Suburb"));

		
		String suburbSuggestion = replaceDataInString(locators.suburbs_input_options,testData.get("Suburb"),testData.get("Suburb"));
		waitForElementToAppear(suburbSuggestion);
		userLeftclick(suburbSuggestion);
		
		//Same here as above | waiting fordropdown to be populated
		sleepTime(2);
		
		//Town

		SelectFromDropdownRandomly(locators.Towns_select);
		
		//City
		SelectFromDropdownRandomly(locators.Cities_select);

		//Province
		SelectFromDropdownRandomly(locators.Province_select);

		//Postal Code
		fillInputValue(locators.Postalcode_input,testData.get("Postal Code"));
		
		//Subsidiary or Division 
		fillInputValue(locators.Subsidiaryordivision_input, testData.get("Subsidiary Or Division"));
		
		//Subsidiary or Division Option
		userLeftclick(locators.autocomplete_input_option);
		
		//Worksite Location
		fillInputValue(locators.WorksiteName_input, testData.get("Worksite Name"));
		
		//Worksite Location
		userLeftclick(locators.autocomplete_input_option);
		
		//Sector
		SelectFromDropdownRandomly(locators.Sector_select);

		//Industry
		SelectFromDropdownRandomly(locators.Industry_select);
		
		//Telephone
		fillInputValue(locators.Telephone_input, testData.get("Telephone"));
		
		//Email 
		fillInputValue(locators.Emailaddress_input, testData.get("Email"));
		
		//clickOnElement(locators.Save_btn);
		
		//checkNewOrganisationDetailsHaveBeenAdded();
		
	}

	
	public void addNewCompany(){
		
		fillInputValue(locators.company_input_org, testData.get("Company"));
		
		clickOnElement(locators.addnewcompany_btn);
		
		sleepTime(2);
	
		
	}
	
}